package gsc.healingmeal.survey.repository;

import gsc.healingmeal.survey.doamin.Survey;
import gsc.healingmeal.survey.doamin.SurveyResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SurveyRepository extends JpaRepository<Survey,Long> {
    boolean existsSurveyByUserId(Long userId);
    Survey findSurveyByUserId(Long userId);
}
