package canarin.lowfare.service;

import canarin.lowfare.model.Flight;
import canarin.lowfare.model.FlightOffer;
import canarin.lowfare.model.ParamQuery;
import canarin.lowfare.repository.FlightRepository;
import canarin.lowfare.repository.ParamQueryRepository;
import canarin.lowfare.rest.Amadeus;
import canarin.lowfare.util.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightService {

    @Value("${AMADEUS_CLIENT_ID}")
    private String AMADEUS_CLIENT_ID;
    @Value("${AMADEUS_CLIENT_SECRET}")
    private String AMADEUS_CLIENT_SECRET;

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private ParamQueryRepository paramQueryRepository;

    @Transactional
    public List<Flight> flightSearch(Params searchRequest) throws Exception {

        // TODO: Pametniji način provjere, npr. Optional<ParamQuery>

//        List<ParamQuery>  paramQueries = paramQueryRepository.findAll().stream()
//            .filter(paramQuery -> paramQuery.getUrlQuery().equals(searchRequest.toQueryString()))
//            .distinct()
//            .collect(Collectors.toList());
//
//        if (paramQueries.size() != 0 ) {
//            return flightRepository.findAll().stream()
//                .filter(flight -> flight.getParam().equals(paramQueries.get(0))) // bolji način smislit
//                .collect(Collectors.toList());
//        }

        ParamQuery paramQueryToSave = new ParamQuery();
        paramQueryToSave.setUrlQuery(searchRequest.toQueryString());

        List<Flight> results = new ArrayList<>();
        System.out.println(searchRequest.toQueryString());

        Amadeus amadeus = Amadeus
            .builder(AMADEUS_CLIENT_ID, AMADEUS_CLIENT_SECRET)
            .build();

        FlightOffer[] flightOffers = amadeus.shopping.flightOffers.get(searchRequest);
        for (FlightOffer flightOffer : flightOffers){
            FlightOffer.FlightSegment flightSegment = flightOffer.getOfferItems()[0].getServices()[0].getSegments()[0].getFlightSegment();
            FlightOffer.Price price = flightOffer.getOfferItems()[0].getPrice();

            Flight flightToSave = new Flight().builder()
                .origin(flightSegment.getDeparture().getIataCode())
                .destination(flightSegment.getArrival().getIataCode())
                .returnDate(flightSegment.getArrival().getAt())
                .departureDate(flightSegment.getDeparture().getAt())
                .priceTotal(price.getTotal())
                .adults(flightOffer.getOfferItems()[0].getServices()[0].getSegments()[0].getPricingDetailPerAdult().getAvailability())
                .param(paramQueryToSave)
                .build();

            // returns to client
            paramQueryRepository.save(paramQueryToSave);
            flightRepository.save(flightToSave);

            results.add(flightToSave);
        }

        return results;
    }
}
