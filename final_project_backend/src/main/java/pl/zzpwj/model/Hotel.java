package pl.zzpwj.model;

import pl.zzpwj.model.SearchHotelsResult.Address;
import pl.zzpwj.model.SearchHotelsResult.Coordinate;

public class Hotel {

    private String name;
    private Coordinate coordinates;
    private double price;
    private double starRating;
    private Address address;

    public Hotel(String name, Coordinate coordinates, double price, double starRating, Address address) {
        this.name = name;
        this.coordinates = coordinates;
        this.price = price;
        this.starRating = starRating;
        this.address = address;
    }
    public Hotel(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    public double getPrice() { return price; }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getStarRating() {
        return starRating;
    }

    public void setStarRating(double starRating) {
        this.starRating = starRating;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
