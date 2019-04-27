package canarin.airportservice.controller;

import canarin.airportservice.payload.AirportResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/airports")
public class AirportController {
    @GetMapping
    public ResponseEntity<AirportResponse> getCustomers() {
        AirportResponse airportResponse = new AirportResponse();
        airportResponse.setIATA("ZAG");
        return new ResponseEntity<>(airportResponse, HttpStatus.OK);
    }
}
