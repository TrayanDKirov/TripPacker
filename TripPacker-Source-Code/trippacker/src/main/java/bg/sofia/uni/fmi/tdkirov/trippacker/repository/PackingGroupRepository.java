package bg.sofia.uni.fmi.tdkirov.trippacker.repository;

import bg.sofia.uni.fmi.tdkirov.trippacker.model.GroupToPack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PackingGroupRepository extends JpaRepository<GroupToPack, Long> {
    Set<GroupToPack> findByCreatedById(Long createdById);

    boolean existsById(Long id);

    boolean existsByIdAndCreatedById(Long id, Long createdById);
}
