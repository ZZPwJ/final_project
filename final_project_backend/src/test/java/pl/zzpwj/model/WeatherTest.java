package pl.zzpwj.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WeatherTest {

    String weatherString;
    Weather weather;

    @BeforeAll
    public void setUp() {
        weatherString = "{\"maxtemp_c\":35.7,\"mintemp_c\":2.3,\"avgtemp_c\":22.4," +
                "\"maxwind_mph\":150.0,\"totalprecip_mm\":40.0,\"avghumidity\":50.0}";

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        try {
            JsonNode node = mapper.readTree(weatherString);
            weather = mapper.treeToValue(node, Weather.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getMaxTempTest() {
        assertEquals(35.7, weather.getMaxtemp_c());
    }

    @Test
    void getMinTempTest() {
        assertEquals(2.3, weather.getMintemp_c());
    }

    @Test
    void getAvgTempCTest() {
        assertEquals(22.4, weather.getAvgtemp_c());
    }

    @Test
    void getMaxWindMphTest() {
        assertEquals(150.0, weather.getMaxwind_mph());
    }

    @Test
    void getTotalPrecipMMTest() {
        assertEquals(40.0, weather.getTotalprecip_mm());
    }

    @Test
    void getAvgHumidityTest() {
        assertEquals(50.0, weather.getAvghumidity());
    }

}
