package pl.zzpwj.model.comparator;

import org.apache.logging.log4j.util.PropertySource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.zzpwj.model.Flight;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightComparatorTest {


    private Flight expensiveFlight;
    private Flight cheapFlight;
    private Flight middleFlight;
    private List<Flight> flights;

    @BeforeEach
    public void setUp() {
        expensiveFlight =  new Flight(123, 2000, true, new Flight.OutboundLeg(
                new int[]{123, 456},555,222, "anytime"),"date");

        cheapFlight = new Flight(123, 20, true, new Flight.OutboundLeg(
                new int[]{123, 456},555,222, "anytime"),"date");

        middleFlight = new Flight(123, 200, true, new Flight.OutboundLeg(
                new int[]{123, 456},555,222, "anytime"),"date");

        flights = new ArrayList<>();
        //Flights in wrong order
        flights.add(middleFlight);
        flights.add(expensiveFlight);
        flights.add(cheapFlight);
    }

    @Test
    public void FlightCompareTest() {
        //before sorting using Comparator:
        assertEquals(middleFlight, flights.get(0));
        assertEquals(expensiveFlight, flights.get(1));
        assertEquals(cheapFlight, flights.get(2));

        //sorting using Comparator:
        flights.sort(new FlightComparator());

        //after sorting using Comparator:
        assertEquals(cheapFlight, flights.get(0));
        assertEquals(middleFlight, flights.get(1));
        assertEquals(expensiveFlight, flights.get(2));
    }
}