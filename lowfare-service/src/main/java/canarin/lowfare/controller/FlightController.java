package canarin.lowfare.controller;

import canarin.lowfare.model.Flight;
import canarin.lowfare.service.FlightService;
import canarin.lowfare.util.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class FlightController {

    @Autowired
    private FlightService flightService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/flights")
    List<Flight> flightSearch (@RequestBody Params searchRequest){
        try {
            return flightService.flightSearch(searchRequest);
        } catch (Exception e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Flight Not Found.", e);
        }
    }
}
