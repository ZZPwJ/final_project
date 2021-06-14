package pl.zzpwj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpwj.model.*;
import pl.zzpwj.model.comparator.FlightComparator;

import java.io.IOException;
import java.text.ParseException;
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

    public Response getResponseForSpecifiedType() throws IOException, InterruptedException, ParseException {
        Response response = new Response();
        response = flightService.setResponseParameters(response);
        response = hotelService.setResponseParameters(response);
        return response;
    }

}
