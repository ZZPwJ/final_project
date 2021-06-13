package pl.zzpwj.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.zzpwj.model.Flight;
import pl.zzpwj.services.FlightService;

import java.io.IOException;
import java.text.ParseException;

@RestController
public class FlightController {

    private FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // metoda zwracajaca najtanszy lot w podanym czasie i podajac lotnisko poczatkowe i końcowe; jako date możemy podać
    // "anytime" jeśli data nie ma znaczenia (ale jak docelowo będziemy wykorzystywali aplikacja do szukania lotow i
    // hoteli na jakiś konkretny event to raczej zawsze będzie data), jako inboundDate podajemy pusty string, jeśli
    // szukamy lotu w jedną stronę. Końcowo datę można "wyciągnąć" z eventu, natomiast nazwy lotnisk z listy lotnisk z
    // danego miasta. Przykładowy endpoint: http://localhost:8080/flights/anytime/anytime/SFO-sky/JFK-sky
    @GetMapping(path = "/flights/{outboundDate}/{inboundDate}/{originPlace}/{destinationPlace}")
    public Flight getCheapestFlightInSpecifiedDate(@PathVariable String outboundDate, @PathVariable String inboundDate,
                                                   @PathVariable String originPlace, @PathVariable String destinationPlace)
            throws IOException, InterruptedException, ParseException {
        return flightService.getCheapestFlightInSpecifiedDate(outboundDate, inboundDate, originPlace, destinationPlace);

    }
}
