package pl.zzpwj.model.SearchHotelsResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result{
    public int id;
    public String name;
    public double starRating;
    public Urls urls;
    public Address address;
    public GuestReviews guestReviews;
    public List<Landmark> landmarks;
    public List<Object> geoBullets;
    public RatePlan ratePlan;
    public String neighbourhood;
    public Deals deals;
    public Messaging messaging;
    public Badging badging;
    public String pimmsAttributes;
    public Coordinate coordinate;
    public String providerType;
    public int supplierHotelId;
    public String vrBadge;
    public boolean isAlternative;
    public OptimizedThumbUrls optimizedThumbUrls;

    public Result() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStarRating() {
        return starRating;
    }

    public void setStarRating(double starRating) {
        this.starRating = starRating;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public GuestReviews getGuestReviews() {
        return guestReviews;
    }

    public void setGuestReviews(GuestReviews guestReviews) {
        this.guestReviews = guestReviews;
    }

    public List<Landmark> getLandmarks() {
        return landmarks;
    }

    public void setLandmarks(List<Landmark> landmarks) {
        this.landmarks = landmarks;
    }

    public List<Object> getGeoBullets() {
        return geoBullets;
    }

    public void setGeoBullets(List<Object> geoBullets) {
        this.geoBullets = geoBullets;
    }

    public RatePlan getRatePlan() {
        return ratePlan;
    }

    public void setRatePlan(RatePlan ratePlan) {
        this.ratePlan = ratePlan;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public Deals getDeals() {
        return deals;
    }

    public void setDeals(Deals deals) {
        this.deals = deals;
    }

    public Messaging getMessaging() {
        return messaging;
    }

    public void setMessaging(Messaging messaging) {
        this.messaging = messaging;
    }

    public Badging getBadging() {
        return badging;
    }

    public void setBadging(Badging badging) {
        this.badging = badging;
    }

    public String getPimmsAttributes() {
        return pimmsAttributes;
    }

    public void setPimmsAttributes(String pimmsAttributes) {
        this.pimmsAttributes = pimmsAttributes;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    public int getSupplierHotelId() {
        return supplierHotelId;
    }

    public void setSupplierHotelId(int supplierHotelId) {
        this.supplierHotelId = supplierHotelId;
    }

    public String getVrBadge() {
        return vrBadge;
    }

    public void setVrBadge(String vrBadge) {
        this.vrBadge = vrBadge;
    }

    public boolean isAlternative() {
        return isAlternative;
    }

    public void setAlternative(boolean alternative) {
        isAlternative = alternative;
    }

    public OptimizedThumbUrls getOptimizedThumbUrls() {
        return optimizedThumbUrls;
    }

    public void setOptimizedThumbUrls(OptimizedThumbUrls optimizedThumbUrls) {
        this.optimizedThumbUrls = optimizedThumbUrls;
    }
}


