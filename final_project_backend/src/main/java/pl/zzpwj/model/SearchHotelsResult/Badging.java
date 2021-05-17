package pl.zzpwj.model.SearchHotelsResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Badging {
    public HotelBadge hotelBadge;

    public Badging() {
    }

    public HotelBadge getHotelBadge() {
        return hotelBadge;
    }

    public void setHotelBadge(HotelBadge hotelBadge) {
        this.hotelBadge = hotelBadge;
    }
}
