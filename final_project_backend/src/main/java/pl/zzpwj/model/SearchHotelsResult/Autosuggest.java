package pl.zzpwj.model.SearchHotelsResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.zzpwj.model.SearchHotelsResult.AdditionalUrlParams;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Autosuggest {
    public AdditionalUrlParams additionalUrlParams;

    public Autosuggest() {
    }

    public AdditionalUrlParams getAdditionalUrlParams() {
        return additionalUrlParams;
    }

    public void setAdditionalUrlParams(AdditionalUrlParams additionalUrlParams) {
        this.additionalUrlParams = additionalUrlParams;
    }
}
