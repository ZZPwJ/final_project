package pl.zzpwj.controllers;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.zzpwj.model.Airport;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
public class HotelController {


    @GetMapping(path="/hotels/{id}")
    public String getHotels(@PathVariable String id) throws IOException, InterruptedException {
        //name of the city (one word name!!) returns string with related locations and suggestions
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://hotels4.p.rapidapi.com/locations/search?query="+ id + "&locale=en_US"))
        .header("x-rapidapi-key", "0db4d58d1fmsh1ee483e08d7f748p154a84jsnd56ea33d9991")
        .header("x-rapidapi-host", "hotels4.p.rapidapi.com")
        .method("GET", HttpRequest.BodyPublishers.noBody())
        .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
