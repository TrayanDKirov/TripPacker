package bg.sofia.uni.fmi.tdkirov.trippacker.repository;

import bg.sofia.uni.fmi.tdkirov.trippacker.model.TripLuggage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TripLuggageRepository extends JpaRepository<TripLuggage, Long> {
    boolean existsById(Long id);

    boolean existsByIdAndCreatedById(Long id, Long createdById);

    Set<TripLuggage> findByCreatedById(Long createdById);
}
