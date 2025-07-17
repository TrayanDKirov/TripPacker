package bg.sofia.uni.fmi.tdkirov.trippacker.repository;

import bg.sofia.uni.fmi.tdkirov.trippacker.model.PackingGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PackingGroupRepository extends JpaRepository<PackingGroup, Long> {
    Set<PackingGroup> findByCreatedById(Long createdById);

    boolean existsById(Long id);

    boolean existsByIdAndCreatedById(Long id, Long createdById);
}
