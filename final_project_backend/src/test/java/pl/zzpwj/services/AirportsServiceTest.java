package pl.zzpwj.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.zzpwj.model.SkyscannerAirport;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class AirportsServiceTest {

    AirportsService airportsService;
    String jsonPlaces;
    ObjectMapper mapper;
    JsonNode node;

    @BeforeEach
    void setUp() {
        airportsService = new AirportsService();
        jsonPlaces = "{\"Places\":[{\"PlaceId\":\"LOND-sky\",\"PlaceName\":\"London\",\"CountryId\":\"UK-sky\"," +
                "\"RegionId\":\"\",\"CityId\":\"LOND-sky\",\"CountryName\":\"United Kingdom\"},{\"PlaceId\":\"LHR-sky\"," +
                "\"PlaceName\":\"London Heathrow\",\"CountryId\":\"UK-sky\",\"RegionId\":\"\",\"CityId\":\"LOND-sky\"," +
                "\"CountryName\":\"United Kingdom\"},{\"PlaceId\":\"LGW-sky\",\"PlaceName\":\"London Gatwick\"," +
                "\"CountryId\":\"UK-sky\",\"RegionId\":\"\",\"CityId\":\"LOND-sky\",\"CountryName\":\"United Kingdom\"}," +
                "{\"PlaceId\":\"STN-sky\",\"PlaceName\":\"London Stansted\",\"CountryId\":\"UK-sky\",\"RegionId\":\"\"," +
                "\"CityId\":\"LOND-sky\",\"CountryName\":\"United Kingdom\"},{\"PlaceId\":\"LTN-sky\"," +
                "\"PlaceName\":\"London Luton\",\"CountryId\":\"UK-sky\",\"RegionId\":\"\",\"CityId\":\"LOND-sky\"," +
                "\"CountryName\":\"United Kingdom\"},{\"PlaceId\":\"LCY-sky\",\"PlaceName\":\"London City\"," +
                "\"CountryId\":\"UK-sky\",\"RegionId\":\"\",\"CityId\":\"LOND-sky\",\"CountryName\":\"United Kingdom\"}," +
                "{\"PlaceId\":\"SEN-sky\",\"PlaceName\":\"London Southend\",\"CountryId\":\"UK-sky\",\"RegionId\":\"\"," +
                "\"CityId\":\"LOND-sky\",\"CountryName\":\"United Kingdom\"}," +
                "{\"PlaceId\":\"LDY-sky\",\"PlaceName\":\"Derry\",\"CountryId\":\"UK-sky\",\"RegionId\":\"\"," +
                "\"CityId\":\"DERR-sky\",\"CountryName\":\"United Kingdom\"},{\"PlaceId\":\"YXU-sky\"," +
                "\"PlaceName\":\"London\",\"CountryId\":\"CA-sky\",\"RegionId\":\"\",\"CityId\":\"YXUS-sky\"," +
                "\"CountryName\":\"Canada\"},{\"PlaceId\":\"ELS-sky\",\"PlaceName\":\"East London\"," +
                "\"CountryId\":\"ZA-sky\",\"RegionId\":\"\",\"CityId\":\"ELSA-sky\",\"CountryName\":\"South Africa\"}]}";

        mapper = new ObjectMapper();
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    @Test
    void getAirportsWithProperCityIdDifferentCitiesIdTest() {
        String jsonPlacesWithDifferentCityId = "{\"Places\":[{\"PlaceId\":\"LHR-sky\",\"PlaceName\":\"London Heathrow\",\"CountryId\":\"UK-sky\"," +
                "\"RegionId\":\"\",\"CityId\":\"LOND-sky\",\"CountryName\":\"United Kingdom\"}," +
                "{\"PlaceId\":\"ELS-sky\",\"PlaceName\":\"East London\"," +
                "\"CountryId\":\"ZA-sky\",\"RegionId\":\"\",\"CityId\":\"ELSA-sky\",\"CountryName\":\"South Africa\"}]}";
        try {
            node = mapper.readTree(jsonPlacesWithDifferentCityId);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        SkyscannerAirport expectedAirport = new SkyscannerAirport();
        expectedAirport.setPlaceId("LHR-sky");
        expectedAirport.setPlaceName("London Heathrow");
        expectedAirport.setCountryId("UK-sky");
        expectedAirport.setRegionId("");
        expectedAirport.setCityId("LOND-sky");
        expectedAirport.setCountryName("United Kingdom");

        List<SkyscannerAirport> expectedAirports = new ArrayList<>();
        expectedAirports.add(expectedAirport);

        List<SkyscannerAirport> actualAirports = airportsService.getAirportsWithProperCityId("London", mapper, node);

        assertTrue(expectedAirports.equals(actualAirports));
    }

    //We reject Airports with same placeId as CityId in getAirportsWithProperCityId
    @Test
    void getAirportsWithProperCityIdPlaceIdSameAsCityIdTest() {

        String jsonPlacesWithSamePlaceIdAsCityId = "{\"Places\":[{\"PlaceId\":\"LHR-sky\",\"PlaceName\":\"London Heathrow\",\"CountryId\":\"UK-sky\"," +
                "\"RegionId\":\"\",\"CityId\":\"LOND-sky\",\"CountryName\":\"United Kingdom\"}," +
                "{\"PlaceId\":\"LOND-sky\",\"PlaceName\":\"London\",\"CountryId\":\"UK-sky\"," +
                "\"RegionId\":\"\",\"CityId\":\"LOND-sky\",\"CountryName\":\"United Kingdom\"}]}";
        try {
            node = mapper.readTree(jsonPlacesWithSamePlaceIdAsCityId);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        SkyscannerAirport expectedAirport = new SkyscannerAirport();
        expectedAirport.setPlaceId("LHR-sky");
        expectedAirport.setPlaceName("London Heathrow");
        expectedAirport.setCountryId("UK-sky");
        expectedAirport.setRegionId("");
        expectedAirport.setCityId("LOND-sky");
        expectedAirport.setCountryName("United Kingdom");

        List<SkyscannerAirport> expectedAirports = new ArrayList<>();
        expectedAirports.add(expectedAirport);

        List<SkyscannerAirport> actualAirports = airportsService.getAirportsWithProperCityId("London", mapper, node);

        assertEquals(actualAirports, expectedAirports);
    }


}