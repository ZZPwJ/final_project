package pl.zzpwj.model.SearchHotelsResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdditionalUrlParams {
    @JsonProperty("resolved-location")
    public String resolvedLocation;
    @JsonProperty("q-destination")
    public String qDestination;
    @JsonProperty("destination-id")
    public String destinationId;

    public AdditionalUrlParams() {
    }

    public String getResolvedLocation() {
        return resolvedLocation;
    }

    public void setResolvedLocation(String resolvedLocation) {
        this.resolvedLocation = resolvedLocation;
    }

    public String getqDestination() {
        return qDestination;
    }

    public void setqDestination(String qDestination) {
        this.qDestination = qDestination;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }
}
