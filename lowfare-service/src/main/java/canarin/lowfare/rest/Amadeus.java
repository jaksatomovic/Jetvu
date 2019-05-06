package canarin.lowfare.rest;

import canarin.lowfare.model.Shopping;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@ToString
public class Amadeus extends HTTPClient {

    private @Getter String clientId;
    private @Getter String clientSecret;

    public Shopping shopping;

    public Amadeus(String clientId, String clientSecret) {
        super();
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.shopping = new Shopping(this);
    }

    public Amadeus build() throws NullPointerException {
        return new Amadeus(clientId, clientSecret);
    }

    public static Amadeus builder(@NonNull String clientId, @NonNull String clientSecret) {
        return new Amadeus(clientId, clientSecret);
    }
}
