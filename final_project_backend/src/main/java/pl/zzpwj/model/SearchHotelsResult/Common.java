package pl.zzpwj.model.SearchHotelsResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Common {
    public PointOfSale pointOfSale;
    public Tracking tracking;

    public Common() {
    }

    public PointOfSale getPointOfSale() {
        return pointOfSale;
    }

    public void setPointOfSale(PointOfSale pointOfSale) {
        this.pointOfSale = pointOfSale;
    }

    public Tracking getTracking() {
        return tracking;
    }

    public void setTracking(Tracking tracking) {
        this.tracking = tracking;
    }
}
