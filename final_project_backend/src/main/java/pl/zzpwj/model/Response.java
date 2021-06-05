package pl.zzpwj.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Response {
    private AirportInfo originAirportInfo;
    private AirportInfo destAirportInfo;
    private String hotels;
    private List<Attraction> attractions;
    private Flight flight;
    private Weather weather;
}
