package canarin.airportservice.service;

import canarin.airportservice.model.Airport;
import canarin.airportservice.payload.AirportRequest;
import canarin.airportservice.payload.AirportResponse;
import canarin.airportservice.repository.AirportRepository;
import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
