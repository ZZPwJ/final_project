package pl.zzpwj.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AirportInfoTest {

    String airportInfoString;
    AirportInfo airportInfo;

    @BeforeAll
    public void setUp() {
        // as we don't use setters to set values in airportInfo, so in test we will use JsonNode and Mapper to set
        // values in AirportInfo object (by analogy to how it is done in AirportService)
        airportInfoString = "{\"id\":362,\"iata\":\"ARN\",\"icao\":\"ESSA\",\"name\":\"Stockholm Arlanda Airport\"," +
                "\"location\":\"Stockholm, Sweden\",\"street_number\":\"\",\"street\":\"\",\"city\":\"\"," +
                "\"county\":\"\",\"state\":\"Stockholms län\",\"country_iso\":\"SE\",\"country\":\"Sweden\"," +
                "\"postal_code\":\"190 45\",\"phone\":\"+46 10 109 10 00\",\"latitude\":59.64976," +
                "\"longitude\":17.92378,\"uct\":120,\"website\":\"http://www.swedavia.se/arlanda/\"}";

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        try {
            JsonNode node = mapper.readTree(airportInfoString);
            airportInfo = mapper.treeToValue(node, AirportInfo.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getIdTest() {
        assertEquals(362, airportInfo.getId());
    }

    @Test
    void getIataTest() {
        assertEquals("ARN", airportInfo.getIata());
    }

    @Test
    void getIcaoTest() {
        assertEquals("ESSA", airportInfo.getIcao());
    }

    @Test
    void getNameTest() {
        assertEquals("Stockholm Arlanda Airport", airportInfo.getName());
    }

    @Test
    void getLocationTest() {
        assertEquals("Stockholm, Sweden", airportInfo.getLocation());
    }

    @Test
    void getStreet_numberTest() {
        assertEquals("", airportInfo.getStreet_number());
    }

    @Test
    void getStreetTest() { assertEquals("", airportInfo.getStreet()); }

    @Test
    void getCityTest() {
        assertEquals("", airportInfo.getCity());
    }

    @Test
    void getCountyTest() { assertEquals("", airportInfo.getCounty()); }

    @Test
    void getStateTest() {
        assertEquals("Stockholms län", airportInfo.getState());
    }

    @Test
    void getCountry_isoTest() {
        assertEquals("SE", airportInfo.getCountry_iso());
    }

    @Test
    void getCountryTest() {
        assertEquals("Sweden", airportInfo.getCountry());
    }

    @Test
    void getPostal_codeTest() {
        assertEquals("190 45", airportInfo.getPostal_code());
    }

    @Test
    void getPhoneTest() {
        assertEquals("+46 10 109 10 00", airportInfo.getPhone());
    }

    @Test
    void getLatitudeTest() {
        assertEquals(59.64976, airportInfo.getLatitude());
    }

    @Test
    void getLongitudeTest() {
        assertEquals(17.92378, airportInfo.getLongitude());
    }

    @Test
    void getUctTest() {
        assertEquals(120, airportInfo.getUct());
    }

    @Test
    void getWebsiteTest() {
        assertEquals("http://www.swedavia.se/arlanda/", airportInfo.getWebsite());
    }
}