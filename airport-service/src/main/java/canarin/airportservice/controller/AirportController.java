package canarin.airportservice.controller;

import canarin.airportservice.model.Airport;
import canarin.airportservice.payload.AirportRequest;
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
    public List<Airport> getAll() {
        return airportService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> addAirport(@RequestBody @Valid @NonNull AirportRequest airportRequest){
        return airportService.addAirport(airportRequest);
    }
}
