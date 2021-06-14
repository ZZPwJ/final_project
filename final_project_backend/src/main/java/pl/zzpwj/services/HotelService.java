package pl.zzpwj.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpwj.model.Hotel;
import pl.zzpwj.model.Response;
import pl.zzpwj.model.SearchHotelsResult.Landmark;
import pl.zzpwj.model.SearchHotelsResult.Result;
import pl.zzpwj.model.SearchHotelsResult.Root;
import pl.zzpwj.model.SearchParameters;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


@Service
public class HotelService {

    Root result;
    SearchParameters searchParameters;
    /*
    Sort options:
    BEST_SELLER|STAR_RATING_HIGHEST_FIRST|STAR_RATING_LOWEST_FIRST|
    DISTANCE_FROM_LANDMARK|GUEST_RATING|PRICE_HIGHEST_FIRST|PRICE
     */
    String sortType;


    public SearchParameters getSearchParameters() {
        return searchParameters;
    }

    public void setSearchParameters(SearchParameters searchParameters){
        this.searchParameters = searchParameters;
    }


    public int getDestinationId(String destination) throws IOException, InterruptedException {
        if(destination.contains(" ")){
            System.out.println("two word name");
            destination = destination.substring(0, destination.indexOf(" ")) + "%20" + destination.substring(destination.indexOf(" ")+1, destination.length());
            System.out.println("new string is: " + destination);
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://hotels4.p.rapidapi.com/locations/search?query=" + destination + "&locale=en_US"))
                .header("x-rapidapi-key", "0db4d58d1fmsh1ee483e08d7f748p154a84jsnd56ea33d9991")
                .header("x-rapidapi-host", "hotels4.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(response.body());
//        System.out.println("destination id is: " + node.get("suggestions").get(0).get("entities").get(0).get("destinationId").asInt());
        return node.get("suggestions").get(0).get("entities").get(0).get("destinationId").asInt();
    }

    public String getRequestUrl(String cityId) throws IOException, InterruptedException {
        int destination = getDestinationId(cityId);
        int numberOfPeople = searchParameters.getNumberOfPeople();
        System.out.println("checkin date: " + searchParameters.getCheckIn() + " checkout date: " + searchParameters.getCheckOut());
        String checkIn = searchParameters.getCheckIn();
        String checkOut = searchParameters.getCheckOut();
        String requestUrl;
        if (searchParameters.isMaxPriceSet()) {
            requestUrl = "https://hotels4.p.rapidapi.com/properties/list?adults1="+ numberOfPeople + "&pageNumber=1&destinationId=" + destination + "&pageSize=25&checkOut="+ checkOut +"&checkIn="+ checkIn + "&priceMax=" + searchParameters.getMaxPrice() + "&sortOrder="+sortType+"&locale=en_US&currency=USD";

        } else {
            requestUrl = "https://hotels4.p.rapidapi.com/properties/list?adults1="+ numberOfPeople + "&pageNumber=1&destinationId=" + destination + "&pageSize=25&checkOut="+ checkOut +"&checkIn="+ checkIn + "&sortOrder="+sortType+"&locale=en_US&currency=USD";

        }
        requestUrl += "&priceMin=" + searchParameters.getMinPrice();
        return requestUrl;
    }

    public Response getHotelList(String cityId, Response response) throws IOException, InterruptedException {

        String url = getRequestUrl(cityId);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("x-rapidapi-key", "0db4d58d1fmsh1ee483e08d7f748p154a84jsnd56ea33d9991")
                .header("x-rapidapi-host", "hotels4.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> httpResponse = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        result = objectMapper.readValue(httpResponse.body(), Root.class);

        Result res = result.getData().getBody().getSearchResults().getResults().get(0);
        Hotel hotel = new Hotel(res.getName(), res.getCoordinate(), res.getRatePlan().getPrice().getExactCurrent(), res.getStarRating(), res.getAddress());
        List<Landmark> landmarks = res.getLandmarks();
        response.setHotel(hotel);
        response.setLandmarks(landmarks);
        return response;

    }

    public Response setResponseParameters(Response response) throws IOException, InterruptedException {
        if(searchParameters.getType().equals("cheapest")){
            sortType = "PRICE";
        } else if(searchParameters.getType().equals("premium")){
            sortType = "PRICE_HIGHEST_FIRST";
        } else {
            sortType = "BEST_SELLER";
        }
        return getHotelList(searchParameters.getDestinationCity(), response);
    }
}
