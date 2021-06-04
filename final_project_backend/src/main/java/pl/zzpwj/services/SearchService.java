package pl.zzpwj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpwj.model.Flight;
import pl.zzpwj.model.Response;
import pl.zzpwj.model.SearchParameters;
import pl.zzpwj.model.SkyscannerAirport;
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

    @Autowired
    public void setServices(FlightService flightService, AirportsService airportsService) {
        this.flightService = flightService;
        this.airportsService = airportsService;
    }
    public SearchParameters getSearchParameters() {
        return searchParameters;
    }

    public void setSearchParameters(SearchParameters searchParameters) {
        this.searchParameters = searchParameters;
    }

    public Response getResponseForSpecifiedType() throws IOException, InterruptedException {
        Response response = null;
        if (searchParameters.getType().equals("cheapest")) {
            response = findCheapestTravel();
        }
        //todo: połaczyć z reszta endpointow, naprawic, że nie chce zwracać Flight
        return response;
    }

    private Response findCheapestTravel() throws IOException, InterruptedException {
        Response cheapestTravel = new Response();
        List<SkyscannerAirport> originAirports = airportsService.getAllCountries(searchParameters.getOriginCity());
        List<SkyscannerAirport> destAirports = airportsService.getAllCountries(searchParameters.getDestinationCity());

        List<Flight> allFlights = new ArrayList<>();
        for (SkyscannerAirport originAirport : originAirports) {
            for (SkyscannerAirport destAirport : destAirports) {
                Flight cheapestFlight = flightService.getCheapestFlightInSpecifiedDate(
                        searchParameters.getCheckIn(), searchParameters.getCheckOut(),
                        originAirport.getPlaceId(), destAirport.getPlaceId());
                if (cheapestFlight != null) {
                    allFlights.add(cheapestFlight);
                }
            }
        }
        Flight cheapestFlightFromAllFlights = Collections.min(allFlights, new FlightComparator());

        cheapestTravel.setFlight(cheapestFlightFromAllFlights);

        return cheapestTravel;
    }

}
