package pl.zzpwj.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpwj.model.Airport;
import pl.zzpwj.model.SkyscannerAirport;
import pl.zzpwj.repository.AirportsRepository;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

// Klasa na razie przykładowa, ma niewiele wspólnego z rzeczywistoscia,
// stworzyłem po prostu szkielet i sprawdziłem jak działa. Z bazy H2
// korzystałem, końcowo raczej nie będzie wykorzystywana.
@Service
public class AirportsService {

    @Autowired
    private AirportsRepository airportsRepository;

    public Airport getAirport(long id) {
        // tu obsluga wyjatku
        return airportsRepository.findById(id).orElse(null);
    }

    public Airport getAirportByName(String name) {
        return airportsRepository.findByNameIgnoreCase(name);
    }

    public Airport updateAirport(Airport airport) {
        Airport updatedAirport = airportsRepository.save(airport);
        return updatedAirport;
    }

    public Airport addAirport(Airport airport) {
        Airport createdAirport = airportsRepository.save(airport);
        return createdAirport;
    }

    public List<Airport> getAllAirports() {
        return airportsRepository.findAll();
    }

    public List<SkyscannerAirport> getAllCountries(String city_id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/" +
                        "autosuggest/v1.0/UK/GBP/en-GB/?query=" + city_id))
                .header("x-rapidapi-key", "0db4d58d1fmsh1ee483e08d7f748p154a84jsnd56ea33d9991")
                .header("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        JsonNode node = objectMapper.readTree(response.body());

        List<SkyscannerAirport> airports = objectMapper.convertValue(node.get("Places"), new TypeReference<>() {
        });


        return airports.stream()
                .filter(airport -> airport.getCityId().equals(city_id))
                .collect(Collectors.toList());
    }
}
