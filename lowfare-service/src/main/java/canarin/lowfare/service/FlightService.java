package canarin.lowfare.service;

import canarin.lowfare.model.Flight;
import canarin.lowfare.model.FlightOffer;
import canarin.lowfare.repository.FlightRepository;
import canarin.lowfare.rest.Amadeus;
import canarin.lowfare.util.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {

    @Value("${AMADEUS_CLIENT_ID}")
    private String AMADEUS_CLIENT_ID;
    @Value("${AMADEUS_CLIENT_SECRET}")
    private String AMADEUS_CLIENT_SECRET;

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> flightSearch(Params searchRequest) throws Exception {
        List<Flight> results = new ArrayList<>();
        Amadeus amadeus = Amadeus
            .builder(AMADEUS_CLIENT_ID, AMADEUS_CLIENT_SECRET)
            .build();

        // TODO: Add For Stop Also & check if searchRequest exist
        //For NonStop Only
        FlightOffer[] flightOffers = amadeus.shopping.flightOffers.get(searchRequest);
        for (FlightOffer flightOffer : flightOffers){
            FlightOffer.FlightSegment flightSegment = flightOffer.getOfferItems()[0].getServices()[0].getSegments()[0].getFlightSegment();
            FlightOffer.Price price = flightOffer.getOfferItems()[0].getPrice();

            // returns to client
            results.add(new Flight().builder()
                .origin(flightSegment.getDeparture().getIataCode())
                .destination(flightSegment.getArrival().getIataCode())
                .returnDate(flightSegment.getArrival().getIataCode())
                .departureDate(flightSegment.getDeparture().getAt())
//                .stops(flightSegment.getStops().length)
                .priceTotal(price.getTotal())
                .adults(flightOffer.getOfferItems()[0].getServices()[0].getSegments()[0].getPricingDetailPerAdult().getAvailability())
                .build());
        }

        return results;
    }
}
