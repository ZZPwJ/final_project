package pl.zzpwj.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationOfCityAttractionsTest {

    private LocationOfCityAttractions location;
    @BeforeEach
    public void setUp() {
        location = new LocationOfCityAttractions("All", 12.43, 17.89, 50000);
    }

    @Test
    void getQueryTest() {
        assertEquals("{\"query\":\"{ " + "getPlaces(categories:[\\\"All\\\"],lat:12.43" +
                 ",lng:17.89,maxDistMeters:50000) " +
                "{ name,lat,lng,abstract,distance,categories } }\"}", location.getQuery());
    }
}