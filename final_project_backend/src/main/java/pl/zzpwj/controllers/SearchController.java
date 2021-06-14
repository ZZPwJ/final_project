package pl.zzpwj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.zzpwj.model.Firebase.User;
import pl.zzpwj.model.Response;
import pl.zzpwj.model.SearchParameters;
import pl.zzpwj.services.FlightService;
import pl.zzpwj.services.HotelService;
import pl.zzpwj.services.SearchService;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
public class SearchController {

    private final SearchService searchService;
    private SearchParameters searchParameters;
    private final HotelService hotelService;
    private final FlightService flightService;
    private User user;
    // przykladowy endpoint: http://localhost:8080/search parametry do POST:
    // numberOfPeople : 3
    // checkOut : "anytime"
    // checkIn : "anytime"
    // maxPrice : 8000
    // minPrice : 200
    // originCity : "London"
    // destinationCity : "Stockholm"
    // type : "cheapest"
    // maxPriceSet : true
    // minPriceSet : true

    @Autowired
    public SearchController(SearchService searchService, HotelService hotelService, FlightService flightService) {
        this.searchService = searchService;
        this.hotelService = hotelService;
        this.flightService = flightService;
        this.user = new User();
    }

    @GetMapping(path = "/search")
    public SearchParameters getSearchParameters(){
        return this.searchParameters;
    }

    @GetMapping(path = "/result")
    public Response getSearchResult() throws IOException, InterruptedException, ParseException {
        Response response = searchService.getResponseForSpecifiedType();
        user.addSearchResponse(response);
        return response;
    }

    @GetMapping(path = "/searchhistory")
    public List<Response> getUserHistorySearch(){
        return user.getSearchHistory();
    }

    @PostMapping(value="/search")
    public ResponseEntity<SearchParameters> setSearchParameters(@RequestBody SearchParameters searchParameters) throws IOException, InterruptedException {
        searchService.setSearchParameters(searchParameters);
        hotelService.setSearchParameters(searchParameters);
        flightService.setSearchParameters(searchParameters);
        user.setSearchParameters(searchParameters);
        this.searchParameters = searchParameters;
        return new ResponseEntity<SearchParameters>(HttpStatus.CREATED);
    }
/*

    @PatchMapping(value = "/search")
    public ResponseEntity<SearchParameters> changeSearchParameters(@RequestBody Map<String, Object> updates){
        if(updates.containsKey("numberOfPeople")){
            searchParameters.setNumberOfPeople((int) updates.get("numberOfPeople"));
        }
        if(updates.containsKey("checkOut")){
            searchParameters.setCheckOut((String) updates.get("checkOut"));
        }
        if(updates.containsKey("checkIn")){
            searchParameters.setCheckIn((String) updates.get("checkIn"));
        }
        if(updates.containsKey("maxPrice")){
            searchParameters.setMaxPrice((int) updates.get("maxPrice"));
        }
        if(updates.containsKey("minPrice")){
            searchParameters.setMinPrice((int) updates.get("minPrice"));
        }
        if(updates.containsKey("originCity")){
            searchParameters.setOriginCity((String) updates.get("originCity"));
        }
        if(updates.containsKey("destinationCity")){
            searchParameters.setDestinationCity((String) updates.get("destinationCity"));
        }
        if(updates.containsKey("type")){
            searchParameters.setDestinationCity((String) updates.get("type"));
        }
        searchService.setSearchParameters(searchParameters);
        return new ResponseEntity<SearchParameters>(HttpStatus.NO_CONTENT);
    }
*/

}
