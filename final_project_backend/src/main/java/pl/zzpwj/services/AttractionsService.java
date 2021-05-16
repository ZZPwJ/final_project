package pl.zzpwj.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.zzpwj.model.Attraction;
import pl.zzpwj.model.LocationOfCityAttractions;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class AttractionsService {
    public List<Attraction> getAttractionsByLocation(LocationOfCityAttractions locationOfCityAttractions)
            throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://travel-places.p.rapidapi.com/"))
                .header("content-type", "application/json")
                .header("x-rapidapi-key", "0db4d58d1fmsh1ee483e08d7f748p154a84jsnd56ea33d9991")
                .header("x-rapidapi-host", "travel-places.p.rapidapi.com")
                .method("POST", HttpRequest.BodyPublishers.ofString(locationOfCityAttractions.getQuery()))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        JsonNode node = objectMapper.readTree(response.body());

        List<Attraction> attractions = objectMapper.convertValue(
                node.get("data").get("getPlaces"), new TypeReference<>() {
        });

        return attractions;
    }
}
