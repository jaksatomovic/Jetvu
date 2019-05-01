package canarin.lowfare.model;

import lombok.Getter;
import lombok.ToString;

@ToString
public class FlightOffer extends Resource {
    protected FlightOffer() {}

    private @Getter String type;
    private @Getter String id;
    private @Getter OfferItem[] offerItems;

    @ToString
    public class OfferItem {
        protected OfferItem() {}

        private @Getter Service[] services;
        private @Getter Price price;
    }

    @ToString
    public class Service {
        protected Service() {}

        private @Getter Segment[] segments;
    }

    @ToString
    public class Segment {
        protected Segment() {}

        private @Getter FlightSegment flightSegment;
        private @Getter PricingDetail pricingDetailPerAdult;
    }

    @ToString
    public class FlightSegment {
        protected FlightSegment() {}

        private @Getter FlightEndPoint departure;
        private @Getter FlightEndPoint arrival;
        private @Getter FlightStop[] stops;
    }

    @ToString
    public class FlightEndPoint {
        protected FlightEndPoint() {}

        private @Getter String iataCode;
        private @Getter String at;
    }

    @ToString
    public class FlightStop {
        protected FlightStop() {}
        // ništa mi ne treba samo njegov size()
    }

    @ToString
    public class Price {
        protected Price() {}

        private @Getter double total;
        private @Getter double totalTaxes;
    }

    @ToString
    public class PricingDetail {
        protected PricingDetail() {}

        private @Getter int availability;
    }
}
