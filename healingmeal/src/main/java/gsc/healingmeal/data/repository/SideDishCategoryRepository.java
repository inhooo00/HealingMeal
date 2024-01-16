package gsc.healingmeal.data.repository;

import gsc.healingmeal.data.domain.SideDishCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SideDishCategoryRepository extends JpaRepository<SideDishCategory, Long> {
    SideDishCategory findById(long id);
}
