package gsc.healingmeal.data.repository;

import gsc.healingmeal.data.domain.SnackOrTeaCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnackOrTeaCategoryRepository extends JpaRepository<SnackOrTeaCategory, Long> {
}
