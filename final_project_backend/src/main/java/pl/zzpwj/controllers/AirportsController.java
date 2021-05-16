package pl.zzpwj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;
import pl.zzpwj.model.Airport;
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

    @GetMapping(path="/airports", produces = APPLICATION_JSON_VALUE)
    public List<Airport> getAllAirports() {
        return airportsService.getAllAirports();
    }

    @GetMapping(path = "/airports/{id}", produces = APPLICATION_JSON_VALUE)
    public Airport getAirport(@PathVariable long id) {
        return airportsService.getAirport(id);
    }

    @GetMapping(path = "/airports/name/{name}", produces = APPLICATION_JSON_VALUE)
    public Airport getAirportByName(@PathVariable String name) {
        return airportsService.getAirportByName(name);
    }

    @PostMapping(path = "/airports", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Airport addAirport(@RequestBody Airport airport) {
        return airportsService.addAirport(airport);
    }

    @PutMapping(path = "/airports/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Airport update(@PathVariable long id, @RequestBody Airport airport) {
        return airportsService.updateAirport(airport);
    }

    @GetMapping(path="/airports/city/{city_id}", produces = APPLICATION_JSON_VALUE)
    public List<SkyscannerAirport> getAllCountries(@PathVariable String city_id)
            throws IOException, InterruptedException {
        return airportsService.getAllCountries(city_id);
    }
}
