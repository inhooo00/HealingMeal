package gsc.healingmeal.survey.repository;

import gsc.healingmeal.survey.doamin.FilterFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilterFoodRepository extends JpaRepository<FilterFood, Long> {
    FilterFood findFilterFoodBySurveyId(Long surveyId);
}
