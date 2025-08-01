package bg.sofia.uni.fmi.tdkirov.trippacker.repository;

import bg.sofia.uni.fmi.tdkirov.trippacker.model.ItemToPack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemToPackRepository extends JpaRepository<ItemToPack, Long> {
    boolean existsById(Long id);

    boolean existsByIdAndCreatedById(Long id, Long createdById);
}
