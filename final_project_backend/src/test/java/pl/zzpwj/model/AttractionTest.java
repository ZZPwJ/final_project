package pl.zzpwj.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AttractionTest {

    String attractionString;
    Attraction attraction;

    @BeforeAll
    public void setUp() {
        attractionString = "{\"name\":\"Almaden Quicksilver County Park\",\"lat\":37.2075,\"lng\":-121.880833," +
                "\"abstract\":null,\"distance\":25405,\"categories\":[\"NATURE\",\"MAJOR\"]}";

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        try {
            JsonNode node = mapper.readTree(attractionString);
            attraction = mapper.treeToValue(node, Attraction.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getNameTest() {
        assertEquals("Almaden Quicksilver County Park", attraction.getName());
    }

    @Test
    void getLatTest() {
        assertEquals(37.2075, attraction.getLat());
    }

    @Test
    void getLngTest() {
        assertEquals(-121.880833, attraction.getLng());
    }

    @Test
    void getDistanceTest() {
        assertEquals(25405, attraction.getDistance());
    }

    @Test
    void getAbstractTest() { assertNull(attraction.getAbstract()); }

    @Test
    void getCategoriesTest() {
        String[] array = {"NATURE", "MAJOR"};
        assertEquals(Arrays.toString(array), Arrays.toString(attraction.getCategories()));
    }

}