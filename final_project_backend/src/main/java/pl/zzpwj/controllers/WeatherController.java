package pl.zzpwj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.zzpwj.model.Weather;
import pl.zzpwj.services.WeatherService;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping(path="/weather/{city}/{date}", produces=APPLICATION_JSON_VALUE)
    public Weather getWeatherByCityAndDate(@PathVariable String city, @PathVariable String date) {
        try {
            return weatherService.getWeatherByCityAndDate(city, date);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
