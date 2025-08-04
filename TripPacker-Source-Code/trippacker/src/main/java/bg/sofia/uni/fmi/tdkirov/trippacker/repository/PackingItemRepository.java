package bg.sofia.uni.fmi.tdkirov.trippacker.repository;

import bg.sofia.uni.fmi.tdkirov.trippacker.model.PackingItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackingItemRepository extends JpaRepository<PackingItem, Long> {
    boolean existsById(Long id);

    boolean existsByIdAndCreatedById(Long id, Long createdById);
}
