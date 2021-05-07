package pl.zzpwj.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// Klasa na razie przykładowa, ma niewiele wspólnego z rzeczywistoscia,
// stworzyłem po prostu szkielet i sprawdziłem jak działa. Z bazy H2
// korzystałem, końcowo raczej nie będzie wykorzystywana.
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String city;
    private double capacity;
    private double size;
    private String address;

}
