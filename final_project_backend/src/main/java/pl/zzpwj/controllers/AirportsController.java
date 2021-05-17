package pl.zzpwj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.zzpwj.model.AirportInfo;
import pl.zzpwj.model.SkyscannerAirport;
import pl.zzpwj.services.AirportsService;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

// Klasa na razie przykładowa, ma niewiele wspólnego z rzeczywistoscia,
// stworzyłem po prostu szkielet i sprawdziłem jak działa. Z bazy H2
// korzystałem, końcowo raczej nie będzie wykorzystywana.

@RestController
public class AirportsController {

    @Autowired
    private AirportsService airportsService;

    @GetMapping(path="/airports/city/{city_id}", produces = APPLICATION_JSON_VALUE)
    public List<SkyscannerAirport> getAllCountries(@PathVariable String city_id) {
        try {
            return airportsService.getAllCountries(city_id);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(path="/airports/info/{iata}", produces = APPLICATION_JSON_VALUE)
    public AirportInfo getAirportInfo(@PathVariable String iata) {
        try {
            return airportsService.getAirportInfo(iata);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
