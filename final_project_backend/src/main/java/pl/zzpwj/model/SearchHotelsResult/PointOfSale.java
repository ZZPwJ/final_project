package pl.zzpwj.model.SearchHotelsResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.zzpwj.model.SearchHotelsResult.Currency;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PointOfSale {
    public Currency currency;
    public String numberSeparators;
    public String brandName;

    public PointOfSale() {
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getNumberSeparators() {
        return numberSeparators;
    }

    public void setNumberSeparators(String numberSeparators) {
        this.numberSeparators = numberSeparators;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
