package canarin.lowfare.model;

import canarin.lowfare.rest.Amadeus;

public class Shopping {

    private Amadeus client;
    public FlightOffers flightOffers;

    public Shopping(Amadeus client) {
        this.client = client;
        this.flightOffers = new FlightOffers(client);
    }
}
