package gsc.healingmeal.survey.repository;

import gsc.healingmeal.survey.doamin.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SurveyRepository extends JpaRepository<Survey,Long> {
}
