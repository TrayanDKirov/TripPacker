package bg.sofia.uni.fmi.tdkirov.trippacker.repository;

import bg.sofia.uni.fmi.tdkirov.trippacker.model.PackingGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackingGroupRepository extends JpaRepository<PackingGroup, Long> {
    boolean existsById(Long id);

    boolean existsByIdAndCreatedById(Long id, Long createdById);
}
