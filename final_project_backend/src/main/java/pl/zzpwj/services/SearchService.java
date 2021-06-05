package pl.zzpwj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpwj.model.*;
import pl.zzpwj.model.comparator.FlightComparator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SearchService {

    SearchParameters searchParameters;
    FlightService flightService;
    AirportsService airportsService;
    HotelService hotelService;
    WeatherService weatherService;

    @Autowired
    public void setServices(FlightService flightService, AirportsService airportsService, HotelService hotelService,
                            WeatherService weatherService) {
        this.flightService = flightService;
        this.airportsService = airportsService;
        this.hotelService = hotelService;
        this.weatherService = weatherService;
    }
    public SearchParameters getSearchParameters() {
        return searchParameters;
    }

    public void setSearchParameters(SearchParameters searchParameters) {
        this.searchParameters = searchParameters;
    }

    public Response getResponseForSpecifiedType() throws IOException, InterruptedException {
        Response response = new Response();
        if (searchParameters.getType().equals("cheapest")) {
            response.setFlight(findCheapestFlight());
            response.setOriginAirportInfo(findAirportInfoForSpecifiedIata(response.getFlight().getOriginAirportIata()));
            response.setDestAirportInfo(findAirportInfoForSpecifiedIata(response.getFlight().getDestAirportIata()));
//            response.setHotels(findHotelsForDestCity(searchParameters.getDestinationCity()));
            response.setWeather(findWeatherForSpecifiedCityAndDate(searchParameters.getDestinationCity(),
                    response.getFlight().getOutboundLeg().getDepartureDate()));
            //todo: dodac hotele i atrakcje (w nich wykorzystac wspolrzedne z hoteli)
        }

        return response;
    }

    private Weather findWeatherForSpecifiedCityAndDate(String destinationCity, String departureDate)
            throws IOException, InterruptedException {
        String[] convertedTable = departureDate.split("T");
        String convertedDate = convertedTable[0];
        return weatherService.getWeatherByCityAndDate(destinationCity, convertedDate);
    }



    private AirportInfo findAirportInfoForSpecifiedIata(String skyscannerIata)
            throws IOException, InterruptedException {
        // konwersja "JFK-sky" na "JFK"
        String convertedIata = skyscannerIata.substring(0,3);
        return airportsService.getAirportInfo(convertedIata);
    }

    private Flight findCheapestFlight() throws IOException, InterruptedException {
        List<SkyscannerAirport> originAirports = airportsService.getAllCountries(searchParameters.getOriginCity());
        List<SkyscannerAirport> destAirports = airportsService.getAllCountries(searchParameters.getDestinationCity());

        List<Flight> allFlights = new ArrayList<>();
        for (SkyscannerAirport originAirport : originAirports) {
            for (SkyscannerAirport destAirport : destAirports) {
                Flight cheapestFlight = flightService.getCheapestFlightInSpecifiedDate(
                        searchParameters.getCheckIn(), searchParameters.getCheckOut(),
                        originAirport.getPlaceId(), destAirport.getPlaceId());
                cheapestFlight.setOriginAirportIata(originAirport.getPlaceId());
                cheapestFlight.setDestAirportIata(destAirport.getPlaceId());
                if (cheapestFlight.getMinPrice() != null) {
                    allFlights.add(cheapestFlight);
                }
            }
        }
        Flight cheapestFlightFromAllFlights = Collections.min(allFlights, new FlightComparator());

        return cheapestFlightFromAllFlights;
    }

//    private String findHotelsForDestCity(String destinationCity)
//            throws IOException, InterruptedException {
//        return hotelService.getHotelList(destinationCity);
//    }

}
