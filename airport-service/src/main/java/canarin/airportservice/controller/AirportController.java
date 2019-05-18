package canarin.airportservice.controller;

import canarin.airportservice.model.AirportDTO;
import canarin.airportservice.service.AirportService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/airports")
@CrossOrigin(origins = "http://localhost:3000")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping
    public List<AirportDTO> getAll() {
        return airportService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> addAirport(@RequestBody @Valid @NonNull AirportDTO airport){
        return airportService.addAirport(airport);
    }
}
