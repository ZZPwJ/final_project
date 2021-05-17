package pl.zzpwj.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Flight {
    public final int quoteId;
    public final int minPrice;
    public final boolean direct;
    public final OutboundLeg outboundLeg;
    public final String quoteDateTime;

    @JsonCreator
    public Flight(@JsonProperty("QuoteId") int quoteId, @JsonProperty("MinPrice") int minPrice,
                  @JsonProperty("Direct") boolean direct, @JsonProperty("OutboundLeg") OutboundLeg outboundLeg,
                  @JsonProperty("QuoteDateTime") String quoteDateTime){
        this.quoteId = quoteId;
        this.minPrice = minPrice;
        this.direct = direct;
        this.outboundLeg = outboundLeg;
        this.quoteDateTime = quoteDateTime;
    }

    public static final class OutboundLeg {
        public final int[] carrierIds;
        public final int originId;
        public final int destinationId;
        public final String departureDate;

        @JsonCreator
        public OutboundLeg(@JsonProperty("CarrierIds") int[] carrierIds,
                           @JsonProperty("OriginId") int originId, @JsonProperty("DestinationId") int destinationId,
                           @JsonProperty("DepartureDate") String departureDate){
            this.carrierIds = carrierIds;
            this.originId = originId;
            this.destinationId = destinationId;
            this.departureDate = departureDate;
        }
    }
}