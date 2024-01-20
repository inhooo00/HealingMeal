package gsc.healingmeal.data.repository;

import gsc.healingmeal.data.domain.SnackOrTeaCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SnackOrTeaCategoryRepository extends JpaRepository<SnackOrTeaCategory, Long> {
//    SnackOrTeaCategory findById(long id);
    Optional<SnackOrTeaCategory> findById(long id);
}
