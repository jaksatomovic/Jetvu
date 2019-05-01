package canarin.lowfare.rest;

import java.util.Map;

import canarin.lowfare.model.Shopping;
import canarin.lowfare.util.Configuration;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

public class Amadeus extends HTTPClient {

    @Autowired
    private @Getter Configuration configuration;

    public Shopping shopping;

    public Amadeus(Configuration configuration) {
        super(configuration);
        this.shopping = new Shopping(this);
    }

    /**
     * Creates a builder object that can be used to build an Amadeus com.amadeus.client.
     *
     * <pre>
     * Amadeus amadeus = Amadeus.builder("CLIENT_ID", "CLIENT_SECRET").build();
     * </pre>
     *
     * @param clientId     Your API com.amadeus.client credential ID
     * @param clientSecret Your API com.amadeus.client credential secret
     * @return a Configuration object
     */
    public static Configuration builder(@NonNull String clientId, @NonNull String clientSecret) {
        return new Configuration(clientId, clientSecret);
    }

    /**
     * Creates a builder object initialized with the environment variables that can be used to build
     * an Amadeus API com.amadeus.client.
     *
     * <pre>
     * Amadeus amadeus = Amadeus.builder(System.getenv()).build();
     * </pre>
     *
     * @param environment The system environment
     * @return a Configuration object
     */
    public static Configuration builder(Map<String, String> environment) {
        String clientId = environment.get("AMADEUS_CLIENT_ID");
        String clientSecret = environment.get("AMADEUS_CLIENT_ID");

        Configuration configuration = Amadeus.builder(clientId, clientSecret);

        return configuration;
    }
}
