package gsc.healingmeal.data.repository;

import gsc.healingmeal.data.domain.RiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiceCategoryRepository extends JpaRepository<RiceCategory, Long> {
    RiceCategory findById(long id);
}
