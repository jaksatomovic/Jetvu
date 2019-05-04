package canarin.airportservice.service;

import canarin.airportservice.model.Airport;
import canarin.airportservice.payload.AirportRequest;
import canarin.airportservice.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    public List<Airport> findAll() {
        return airportRepository.findAll();
    }

    public ResponseEntity<?> addAirport(AirportRequest airportRequest) {
        Airport airport = new Airport(airportRequest.getName(), airportRequest.getIataCode());
        airportRepository.save(airport);

        return ResponseEntity.ok("Airport added");
    }

    // TODO: add getById, delete and update
}
