package pl.zzpwj.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attraction {
    private String name;
    private double lat;
    private double lng;
    private int distance;
    private String Abstract;
    private String[] categories;
}
