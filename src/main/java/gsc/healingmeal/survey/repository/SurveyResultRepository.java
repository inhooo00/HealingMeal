package gsc.healingmeal.survey.repository;

import gsc.healingmeal.member.domain.User;
import gsc.healingmeal.survey.doamin.SurveyResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyResultRepository extends JpaRepository<SurveyResult, Long> {
    SurveyResult findSurveyResultByUser(User user);

    SurveyResult findByUserId(Long userId);
}
