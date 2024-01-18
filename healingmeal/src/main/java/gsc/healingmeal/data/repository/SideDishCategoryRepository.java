package gsc.healingmeal.data.repository;

import gsc.healingmeal.data.domain.SideDishCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SideDishCategoryRepository extends JpaRepository<SideDishCategory, Long> {
    Optional<SideDishCategory> findById(long id);
}
