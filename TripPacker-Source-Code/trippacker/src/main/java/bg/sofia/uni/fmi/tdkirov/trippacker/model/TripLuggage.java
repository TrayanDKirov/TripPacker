package bg.sofia.uni.fmi.tdkirov.trippacker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "trip_luggage")
public class TripLuggage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public TripLuggage(Long id) {
        this.id = id;
    }
}
