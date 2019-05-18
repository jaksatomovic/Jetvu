package canarin.airportservice.service;

import canarin.airportservice.model.AirportDTO;
import canarin.airportservice.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    public List<AirportDTO> findAll() {
        return airportRepository.findAll();
    }

    public ResponseEntity<?> addAirport(AirportDTO airport) {
        AirportDTO airportDTO = new AirportDTO(airport.getName(), airport.getIATA());
        airportRepository.save(airportDTO);

        return ResponseEntity.ok("AirportDTO added");
    }

    // TODO: add getById, delete and update
}
