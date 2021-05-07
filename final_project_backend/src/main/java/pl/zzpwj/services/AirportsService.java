package pl.zzpwj.services;

import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpwj.model.Airport;
import pl.zzpwj.repository.AirportsRepository;

import java.util.List;

// Klasa na razie przykładowa, ma niewiele wspólnego z rzeczywistoscia,
// stworzyłem po prostu szkielet i sprawdziłem jak działa. Z bazy H2
// korzystałem, końcowo raczej nie będzie wykorzystywana.
@Service
public class AirportsService {

    @Autowired
    private AirportsRepository airportsRepository;

    public Airport getAirport(long id) {
        // tu obsluga wyjatku
        return airportsRepository.findById(id).orElse(null);
    }

    public Airport updateAirport(Airport airport) {
        Airport updatedAirport = airportsRepository.save(airport);
        return updatedAirport;
    }

    public Airport addAirport(Airport airport) {
        Airport createdAirport = airportsRepository.save(airport);
        return createdAirport;
    }

    public List<Airport> getAllAirports() {
        return airportsRepository.findAll();
    }
}
