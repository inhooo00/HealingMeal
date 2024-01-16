package gsc.healingmeal.data.repository;

import gsc.healingmeal.data.domain.MainDishCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainDishCategoryRepository extends JpaRepository<MainDishCategory, Long> {
    MainDishCategory findById(long user_id);
}
