package canarin.lowfare.util;

import canarin.lowfare.rest.Amadeus;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@ToString
public class Configuration {

    private @Getter String clientId;
    private @Getter String clientSecret;

    public Configuration(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public Amadeus build() throws NullPointerException {
        return new Amadeus(this);
    }
}
