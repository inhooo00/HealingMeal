package gsc.healingmeal.data.repository;

import gsc.healingmeal.data.domain.RiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RiceCategoryRepository extends JpaRepository<RiceCategory, Long> {
    Optional<RiceCategory> findById(long id);
}
