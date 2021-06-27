package pl.zzpwj.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.zzpwj.model.SearchHotelsResult.Landmark;

import java.util.List;

@Data
@NoArgsConstructor
public class Response {
    private AirportInfo originAirportInfo;
    private AirportInfo destAirportInfo;
    private Hotel hotel;
    private List<Landmark> landmarks;
    private Flight flight;
    private Flight returnFlight;
    private Weather weather;
}
