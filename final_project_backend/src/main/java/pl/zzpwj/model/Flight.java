package pl.zzpwj.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Flight {
    private final int quoteId;
    private final Integer minPrice;
    private final boolean direct;
    private final OutboundLeg outboundLeg;
    private final String quoteDateTime;

    @JsonCreator
    public Flight(@JsonProperty("QuoteId") int quoteId, @JsonProperty("MinPrice") Integer minPrice,
                  @JsonProperty("Direct") boolean direct, @JsonProperty("OutboundLeg") OutboundLeg outboundLeg,
                  @JsonProperty("QuoteDateTime") String quoteDateTime){
        this.quoteId = quoteId;
        this.minPrice = minPrice;
        this.direct = direct;
        this.outboundLeg = outboundLeg;
        this.quoteDateTime = quoteDateTime;
    }

    public static final class OutboundLeg {
        private final int[] carrierIds;
        private final int originId;
        private final int destinationId;
        private final String departureDate;

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