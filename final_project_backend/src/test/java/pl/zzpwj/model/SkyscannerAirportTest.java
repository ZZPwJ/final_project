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

class SkyscannerAirportTest {

    String skyscannerAirportString;
    SkyscannerAirport skyscannerAirport;

    @BeforeAll
    public void setUp() {
        skyscannerAirportString = "{\"countryId\":\"PL\",\"placeID\":\"MOD\", \"placeName\":\"Modlin\"," +
                " \"regionId\":\"MAZ\", \"cityId\":\"WAW\", \"countryName\":\"Polska\"}";

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        try {
            JsonNode node = mapper.readTree(skyscannerAirportString);
            skyscannerAirport = mapper.treeToValue(node, SkyscannerAirport.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getCountryIdTest() {
        assertEquals("PL", skyscannerAirport.getCountryId());
    }

    @Test
    void getPlaceIdTest() {
        assertEquals("MOD", skyscannerAirport.getPlaceId());
    }

    @Test
    void getRegionIdTest() {
        assertEquals("MAZ", skyscannerAirport.getRegionId());
    }

    @Test
    void getCityIdTest() {
        assertEquals("WAW", skyscannerAirport.getCityId());
    }

    @Test
    void getCountryNameTest() { assertEquals("Polska", skyscannerAirport.getCountryName()); }

}
