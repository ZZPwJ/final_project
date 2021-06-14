package pl.zzpwj.model;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Data
public class SearchParameters {
    static private int numberOfPeople;
    static private String checkOut;
    static private String checkIn;
    static private int maxPrice;
    static private int minPrice;
    static private String originCity;
    static private String destinationCity;
    //type of journal which user will input; for example "cheapest", "highest rated"
    private String type;

    public SearchParameters() {
    }

    public SearchParameters(int numberOfPeople, String checkOut, String checkIn, int maxPrice, int minPrice,
                            String originCity, String destinationCity, String type) {
        this.numberOfPeople = numberOfPeople;
        this.checkOut = checkOut;
        this.checkIn = checkIn;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.originCity = originCity;
        this.destinationCity = destinationCity;
        this.type = type;
    }

    public boolean isMaxPriceSet(){
        if(maxPrice > 0) return true;
        else return false;
    }

    public boolean isMinPriceSet(){
        if(minPrice > 0) return true;
        else return false;
    }

    public void setDateBasedOnFlight(String arrive, String leaveDate) throws ParseException {
        checkIn = arrive.substring(0, 10);
        checkOut = leaveDate.substring(0, 10);
        if (checkOut.equals("anytime")) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date arriveDate = formatter.parse(checkIn);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(arriveDate);
            int weekStay = 7;
            calendar.add(Calendar.DAY_OF_YEAR, weekStay);
            checkOut = formatter.format(calendar.getTime());
            System.out.println("new checkout date " + checkOut);
        }

    }
    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public String getOriginCity() {
        return originCity;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
