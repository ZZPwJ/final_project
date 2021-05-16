package pl.zzpwj.model.SearchHotelsResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.zzpwj.model.SearchHotelsResult.Omniture;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tracking {
    public Omniture omniture;

    public Tracking() {
    }

    public Omniture getOmniture() {
        return omniture;
    }

    public void setOmniture(Omniture omniture) {
        this.omniture = omniture;
    }
}
