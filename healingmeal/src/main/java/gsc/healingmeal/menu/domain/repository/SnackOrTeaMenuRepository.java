package gsc.healingmeal.menu.domain.repository;

import gsc.healingmeal.menu.domain.Meals;
import gsc.healingmeal.menu.domain.SnackOrTea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnackOrTeaMenuRepository extends JpaRepository<SnackOrTea, Long> {
    SnackOrTea findByUserIdAndMeals(long userId, Meals meals);
}
