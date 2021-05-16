package pl.zzpwj.model.SearchHotelsResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString), Root.class); */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Destination {
    public String id;
    public String value;
    public String resolvedLocation;

    public Destination() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getResolvedLocation() {
        return resolvedLocation;
    }

    public void setResolvedLocation(String resolvedLocation) {
        this.resolvedLocation = resolvedLocation;
    }
}
