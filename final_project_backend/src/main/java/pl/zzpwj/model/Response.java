package pl.zzpwj.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Response {
    private AirportInfo airportInfo;
    private List<Attraction> attractions;
    private Flight flight;
    private SkyscannerAirport skyscannerAirport;
    private Weather weather;
}
