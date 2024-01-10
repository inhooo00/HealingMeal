package gsc.healingmeal.survey.contoroller;

import gsc.healingmeal.member.dto.UserSearchDto;
import gsc.healingmeal.member.service.UserJoinService;
import gsc.healingmeal.survey.doamin.Survey;
import gsc.healingmeal.survey.dto.FilterFoodRequestDto;
import gsc.healingmeal.survey.dto.SurveyRequestDto;
import gsc.healingmeal.survey.service.SurveyService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SurveyController {
    private final SurveyService surveyService;


    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping("/{userId}/servey")
    public ResponseEntity<Long> saveServey(@RequestBody SurveyRequestDto surveyRequestDto, @PathVariable Long userId) {
        Survey survey = surveyService.submitSurvey(surveyRequestDto, userId);
        return new ResponseEntity<>(survey.getId(), HttpStatus.OK); // 해당 값을 프론트에서 보내서 filterFood 저장할 때 url로 매핑.
    }


    @PostMapping("/{surveyId}/filterFood")
    public ResponseEntity<String> saveFilter(@RequestBody FilterFoodRequestDto filterFoodRequestDto, @PathVariable Long surveyId) {
        surveyService.submitFilterFood(filterFoodRequestDto, surveyId);
        return new ResponseEntity<>("모든 설문조사 내용 저장!", HttpStatus.OK);
    }


}
