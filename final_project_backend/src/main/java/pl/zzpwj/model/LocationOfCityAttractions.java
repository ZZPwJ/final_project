package pl.zzpwj.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationOfCityAttractions {
    private String category;
    private double lat;
    private double lng;
    private int maxDistMeters;

    public String getQuery() {
        return "{\"query\":\"{ " + "getPlaces(categories:[\\\""+ category + "\\\"],lat:"+ lat
                + ",lng:" + lng + ",maxDistMeters:" + maxDistMeters + ") " +
                "{ name,lat,lng,abstract,distance,categories } }\"}";
    }
}
