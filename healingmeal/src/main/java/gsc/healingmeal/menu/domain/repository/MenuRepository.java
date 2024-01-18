package gsc.healingmeal.menu.domain.repository;

import gsc.healingmeal.menu.domain.Meals;
import gsc.healingmeal.menu.domain.MenuForUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<MenuForUser, Long> {
    MenuForUser findByUserIdAndMeals(long userId, Meals meals);
}
