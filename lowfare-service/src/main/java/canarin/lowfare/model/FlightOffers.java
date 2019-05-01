package canarin.lowfare.model;

import canarin.lowfare.payload.Response;
import canarin.lowfare.rest.Amadeus;
import canarin.lowfare.util.Params;

public class FlightOffers {
    private Amadeus client;

    public FlightOffers(Amadeus client) {
        this.client = client;
    }

    /**
     * <p>
     * Find the cheapest bookable flights.
     * </p>
     *
     * <pre>
     * amadeus.shopping.flightOffers.get(Params
     *   .with("origin", "LHR")
     *   .and("destination", "LAX")
     *   .and("departureDate", "2017-12-24"));</pre>
     *
     * @param params the parameters to send to the API
     * @return an API response object
     * @throws Exception when an exception occurs
     */
    public FlightOffer[] get(Params params) throws Exception {
        Response response = client.get("/v1/shopping/flight-offers", params);
        return (FlightOffer[]) Resource.fromArray(response, FlightOffer[].class);
    }

    /**
     * Convenience method for calling <code>get</code> without any parameters.
     *
     * @see FlightOffers#get()
     */
    public FlightOffer[] get() throws Exception {
        return get(null);
    }
}
