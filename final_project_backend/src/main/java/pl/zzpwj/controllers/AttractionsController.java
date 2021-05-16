package pl.zzpwj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.zzpwj.model.Attraction;
import pl.zzpwj.model.LocationOfCityAttractions;
import pl.zzpwj.services.AttractionsService;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class AttractionsController {
    @Autowired
    private AttractionsService attractionsService;

    @PostMapping(path="/attractions", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public List<Attraction> getAttractionsByLocation(@RequestBody LocationOfCityAttractions locOfCityAttractions) {
        try {
            return attractionsService.getAttractionsByLocation(locOfCityAttractions);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

