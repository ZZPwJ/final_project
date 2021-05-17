package pl.zzpwj.model.SearchHotelsResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Filters {
    public boolean applied;
    public Name name;
    public StarRating starRating;
    public GuestRating guestRating;
    public Landmark landmarks;
    public Neighbourhood neighbourhood;
    public AccommodationType accommodationType;
    public Facilities facilities;
    public Accessibility accessibility;
    public ThemesAndTypes themesAndTypes;
    public Price price;
    public PaymentPreference paymentPreference;
    public WelcomeRewards welcomeRewards;

    public Filters() {
    }

    public boolean isApplied() {
        return applied;
    }

    public void setApplied(boolean applied) {
        this.applied = applied;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public StarRating getStarRating() {
        return starRating;
    }

    public void setStarRating(StarRating starRating) {
        this.starRating = starRating;
    }

    public GuestRating getGuestRating() {
        return guestRating;
    }

    public void setGuestRating(GuestRating guestRating) {
        this.guestRating = guestRating;
    }

    public Landmark getLandmarks() {
        return landmarks;
    }

    public void setLandmarks(Landmark landmarks) {
        this.landmarks = landmarks;
    }

    public Neighbourhood getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(Neighbourhood neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public AccommodationType getAccommodationType() {
        return accommodationType;
    }

    public void setAccommodationType(AccommodationType accommodationType) {
        this.accommodationType = accommodationType;
    }

    public Facilities getFacilities() {
        return facilities;
    }

    public void setFacilities(Facilities facilities) {
        this.facilities = facilities;
    }

    public Accessibility getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(Accessibility accessibility) {
        this.accessibility = accessibility;
    }

    public ThemesAndTypes getThemesAndTypes() {
        return themesAndTypes;
    }

    public void setThemesAndTypes(ThemesAndTypes themesAndTypes) {
        this.themesAndTypes = themesAndTypes;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public PaymentPreference getPaymentPreference() {
        return paymentPreference;
    }

    public void setPaymentPreference(PaymentPreference paymentPreference) {
        this.paymentPreference = paymentPreference;
    }

    public WelcomeRewards getWelcomeRewards() {
        return welcomeRewards;
    }

    public void setWelcomeRewards(WelcomeRewards welcomeRewards) {
        this.welcomeRewards = welcomeRewards;
    }
}
