package pl.zzpwj.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.zzpwj.model.AirportInfo;
import pl.zzpwj.model.SkyscannerAirport;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


@Service
public class AirportsService {

    public List<SkyscannerAirport> getAllCountries(String city_name) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/" +
                        "autosuggest/v1.0/UK/GBP/en-GB/?query=" + city_name))
                .header("x-rapidapi-key", "0db4d58d1fmsh1ee483e08d7f748p154a84jsnd56ea33d9991")
                .header("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
//                .header("includeCities", "false")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        JsonNode node = objectMapper.readTree(response.body());
        return getAirportsWithProperCityId(city_name, objectMapper, node);
    }

//        porównujemy, czy np. przy wpisaniu Vienna lotniska maja cityId VIEN-sky, bo jeśli nie sprawdzimy
//        to przy wpisaniu Vienna znajduje np dla Wietnamu.a
    public List<SkyscannerAirport> getAirportsWithProperCityId(String city_name, ObjectMapper objectMapper,
                                                                JsonNode node) {
        JsonNode placesNode = node.get("Places");
        List<SkyscannerAirport> airports = new ArrayList<>();
        for (int i = 0; i < placesNode.size(); i++) {
            JsonNode arrayElement = placesNode.get(i);
            String cityId = arrayElement.get("CityId").textValue();
            // musimy odrzucić te elementy w ktorych placeId == cityId, bo jest to w skyscannerze miasto, a
            // nie lotnisko (np LOND-sky jako placeId i jako cityId)
            String placeId = arrayElement.get("PlaceId").textValue();
            String convertedCityName = (city_name.substring(0, 4).toUpperCase()) + "-sky";
            if (cityId.equals(convertedCityName) && !placeId.equals(convertedCityName)) {
                airports.add(objectMapper.convertValue(arrayElement, new TypeReference<>() {
                }));
            }
        }
        return airports;
    }

    @JsonIgnoreProperties
    public AirportInfo getAirportInfo(String iata) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://airport-info.p.rapidapi.com/airport?iata=" + iata))
                .header("x-rapidapi-key", "0db4d58d1fmsh1ee483e08d7f748p154a84jsnd56ea33d9991")
                .header("x-rapidapi-host", "airport-info.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        JsonNode node = objectMapper.readTree(response.body());

        System.out.println(response.body());
        AirportInfo airportInfo = objectMapper.treeToValue(node, AirportInfo.class);
        return airportInfo;
    }
}
