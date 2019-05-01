package canarin.airportservice.payload;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter @Setter
public class AirportRequest {

    @NonNull
    private String name;
    @NonNull
    private String iataCode;
}
