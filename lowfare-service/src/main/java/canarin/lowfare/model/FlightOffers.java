package canarin.lowfare.model;

import canarin.lowfare.payload.Response;
import canarin.lowfare.rest.Amadeus;
import canarin.lowfare.util.Params;

public class FlightOffers {
    private Amadeus client;

    public FlightOffers(Amadeus client) {
        this.client = client;
    }

    public FlightOffer[] get(Params params) throws Exception {
        Response response = client.get("/v1/shopping/flight-offers", params);
        return (FlightOffer[]) Resource.fromArray(response, FlightOffer[].class);
    }

    public FlightOffer[] get() throws Exception {
        return get(null);
    }
}
