package pl.zzpwj.model.SearchHotelsResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.zzpwj.model.SearchHotelsResult.Destination;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Query {
    public Destination destination;

    public Query() {
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}
