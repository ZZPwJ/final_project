package pl.zzpwj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zzpwj.model.Airport;

// Klasa na razie przykładowa, ma niewiele wspólnego z rzeczywistoscia,
// stworzyłem po prostu szkielet i sprawdziłem jak działa. Z bazy H2
// korzystałem, końcowo raczej nie będzie wykorzystywana.
@Repository
public interface AirportsRepository extends JpaRepository<Airport, Long> {
    Airport findByNameIgnoreCase(String name);
}
