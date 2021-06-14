package pl.zzpwj.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpwj.model.*;
import pl.zzpwj.model.comparator.FlightComparator;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    AirportsService airportsService;
    @Autowired
    WeatherService weatherService;
    SearchParameters searchParameters;

    public SearchParameters getSearchParameters() {
        return searchParameters;
    }

    public void setSearchParameters(SearchParameters searchParameters) {
        this.searchParameters = searchParameters;
    }

    public Flight getCheapestFlightInSpecifiedDate(String outboundDate, String inboundDate,
                                                   String originPlace, String destinationPlace)
            throws IOException, InterruptedException, ParseException {

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

        Flight cheapestFlight = objectMapper.treeToValue(node.get("Quotes").get(0), Flight.class);

        return cheapestFlight;

    }

    public Response setResponseParameters(Response response) throws IOException, InterruptedException, ParseException {
        if (searchParameters.getType().equals("cheapest")) {
            response.setFlight(findCheapestFlight(0));
            response.setReturnFlight(findCheapestFlight(1));
        } else if (searchParameters.getType().equals("premium")) {
            response.setFlight(findExpensiveDirectFlight(0));
            response.setReturnFlight(findExpensiveDirectFlight(1));
        } else {
            response.setFlight(findCheapestDirectFlight(0));
            response.setReturnFlight(findCheapestDirectFlight(1));
        }
        if (searchParameters.getCheckIn().equals("anytime") || searchParameters.getCheckOut().equals("anytime")) {
            searchParameters.setDateBasedOnFlight(response.getFlight().getOutboundLeg().getDepartureDate(),
                                                response.getReturnFlight().getOutboundLeg().getDepartureDate());
        }

        response.setOriginAirportInfo(findAirportInfoForSpecifiedIata(response.getFlight().getOriginAirportIata()));
        response.setDestAirportInfo(findAirportInfoForSpecifiedIata(response.getFlight().getDestAirportIata()));
//            response.setHotels(findHotelsForDestCity(searchParameters.getDestinationCity()));
        response.setWeather(weatherService.findWeatherForSpecifiedCityAndDate(searchParameters.getDestinationCity(),
                response.getFlight().getOutboundLeg().getDepartureDate()));
        return response;
    }


    private AirportInfo findAirportInfoForSpecifiedIata(String skyscannerIata)
            throws IOException, InterruptedException {
        // konwersja "JFK-sky" na "JFK"
        String convertedIata = skyscannerIata.substring(0,3);
        return airportsService.getAirportInfo(convertedIata);
    }

    private Flight findCheapestFlight(int type) throws IOException, InterruptedException, ParseException {
        System.out.println(searchParameters.getOriginCity());
        List<Flight> allFlights = findAllFlights(type);
        Flight cheapestFlightFromAllFlights = Collections.min(allFlights, new FlightComparator());
//        if(searchParameters.getCheckIn().equals("anytime")){
//            searchParameters.setDateBasedOnFlight(cheapestFlightFromAllFlights.getOutboundLeg().getDepartureDate());
//        }
        return cheapestFlightFromAllFlights;
    }

    private Flight findCheapestDirectFlight(int type) throws IOException, ParseException, InterruptedException {
        List<Flight> allFlights = findAllFlights(type);
        List<Flight> directFlights = getDirectFlights(allFlights);
        Flight cheapestDirectFlight;
        if (directFlights.size() == 0) {
            cheapestDirectFlight = Collections.min(allFlights, new FlightComparator());
        } else {
            cheapestDirectFlight = Collections.min(directFlights, new FlightComparator());
        }
//        if(searchParameters.getCheckIn().equals("anytime")){
//            searchParameters.setDateBasedOnFlight(cheapestDirectFlight.getOutboundLeg().getDepartureDate());
//        }
        return cheapestDirectFlight;
    }


    private Flight findExpensiveDirectFlight(int type) throws IOException, ParseException, InterruptedException {
        List<Flight> allFlights = findAllFlights(type);
        List<Flight> directFlights = getDirectFlights(allFlights);
        Flight mostExpensiveFlight;
        if (directFlights.size() == 0) {
            mostExpensiveFlight = Collections.max(allFlights, new FlightComparator());
        } else {
            mostExpensiveFlight = Collections.max(directFlights, new FlightComparator());
        }
//        if(searchParameters.getCheckIn().equals("anytime")){
//            searchParameters.setDateBasedOnFlight(mostExpensiveFlight.getOutboundLeg().getDepartureDate());
//        }
        return mostExpensiveFlight;
    }

    //type : 0 - first-way, 1 - return
    private List<Flight> findAllFlights(int type) throws IOException, InterruptedException, ParseException {
        List<SkyscannerAirport> originAirports;
        List<SkyscannerAirport> destAirports;
        if (type == 0) {
            originAirports = airportsService.getAllCountries(searchParameters.getOriginCity());
            destAirports = airportsService.getAllCountries(searchParameters.getDestinationCity());
        } else {
            originAirports = airportsService.getAllCountries(searchParameters.getDestinationCity());
            destAirports = airportsService.getAllCountries(searchParameters.getOriginCity());
        }
        List<Flight> allFlights = new ArrayList<>();
        for (SkyscannerAirport originAirport : originAirports) {
            for (SkyscannerAirport destAirport : destAirports) {
                Flight cheapestFlight;
                if (type == 0) {
                    cheapestFlight = getCheapestFlightInSpecifiedDate(
                            searchParameters.getCheckIn(), searchParameters.getCheckOut(),
                            originAirport.getPlaceId(), destAirport.getPlaceId());
                } else {
                    cheapestFlight = getCheapestFlightInSpecifiedDate(
                            searchParameters.getCheckOut(), "anytime",
                            originAirport.getPlaceId(), destAirport.getPlaceId());
                }
                if(cheapestFlight != null) {
                    cheapestFlight.setOriginAirportIata(originAirport.getPlaceId());
                    cheapestFlight.setDestAirportIata(destAirport.getPlaceId());
                    if (cheapestFlight.getMinPrice() != null) {
                        allFlights.add(cheapestFlight);
                    }
                }
            }
        }
        return allFlights;
    }

    private List<Flight> getDirectFlights(List<Flight> allFlights) {
        List<Flight> directFlights = new ArrayList<>();
        for (Flight flight : allFlights) {
            if (flight.isDirect()) {
                directFlights.add(flight);
            }
        }
        return directFlights;
    }

}
