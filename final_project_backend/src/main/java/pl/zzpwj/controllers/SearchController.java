package pl.zzpwj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.zzpwj.model.SearchParameters;
import pl.zzpwj.services.HotelService;

import java.util.Map;

@RestController
public class SearchController {

    private final HotelService hotelService;
    private SearchParameters searchParameters;

    @Autowired
    public SearchController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping(value="/search")
    public ResponseEntity<SearchParameters> setSearchParameters(@RequestBody SearchParameters searchParameters){
        hotelService.setSearchParameters(searchParameters);
        this.searchParameters = searchParameters;
        return new ResponseEntity<SearchParameters>(HttpStatus.CREATED);
    }

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
        hotelService.setSearchParameters(searchParameters);
        return new ResponseEntity<SearchParameters>(HttpStatus.NO_CONTENT);
    }

}
