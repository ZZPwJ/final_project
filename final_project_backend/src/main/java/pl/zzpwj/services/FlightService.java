package pl.zzpwj.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class FlightService {

    public JsonNode getCheapestFlightInSpecifiedDate(String outboundDate, String inboundDate,
                                                 String originPlace, String destinationPlace)
            throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsequotes/v1.0/PL/EUR/pl-PL/"
                        + originPlace + "/" + destinationPlace + "/" + outboundDate + "?inboundpartialdate=" +
                        inboundDate))
                .header("x-rapidapi-key", "0db4d58d1fmsh1ee483e08d7f748p154a84jsnd56ea33d9991")
                .header("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(response.body());

        return node.get("Quotes").get(0);

    }
}
