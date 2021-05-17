package pl.zzpwj.model.SearchHotelsResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Features {
    public boolean paymentPreference;
    public boolean noCCRequired;

    public Features() {
    }

    public boolean isPaymentPreference() {
        return paymentPreference;
    }

    public void setPaymentPreference(boolean paymentPreference) {
        this.paymentPreference = paymentPreference;
    }

    public boolean isNoCCRequired() {
        return noCCRequired;
    }

    public void setNoCCRequired(boolean noCCRequired) {
        this.noCCRequired = noCCRequired;
    }
}
