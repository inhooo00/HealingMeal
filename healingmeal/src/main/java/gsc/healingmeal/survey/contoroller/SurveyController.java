package gsc.healingmeal.survey.contoroller;


import gsc.healingmeal.survey.doamin.FilterFood;
import gsc.healingmeal.survey.doamin.Survey;
import gsc.healingmeal.survey.doamin.SurveyResult;
import gsc.healingmeal.survey.dto.FilterFoodRequestDto;
import gsc.healingmeal.survey.dto.SurveyRequestDto;
import gsc.healingmeal.survey.dto.SurveyResultDto;
import gsc.healingmeal.survey.service.SurveyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SurveyController {
    private final SurveyService surveyService;


    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }
    // 설문 저장.
    @PostMapping("/{userId}/survey")
    public ResponseEntity<Long> saveServey(@RequestBody SurveyRequestDto surveyRequestDto, @PathVariable Long userId) {
        Survey survey = surveyService.submitSurvey(surveyRequestDto, userId);
        return new ResponseEntity<>(survey.getId(), HttpStatus.OK); // 해당 값을 프론트에서 보내서 filterFood 저장할 때 url로 매핑.
    }

    // 음식 필터 설문 저장.
    @PostMapping("/{surveyId}/filterFood")
    public ResponseEntity<Long> saveFilter(@RequestBody FilterFoodRequestDto filterFoodRequestDto, @PathVariable Long surveyId) {
       FilterFood filterFood = surveyService.submitFilterFood(filterFoodRequestDto, surveyId);
        return new ResponseEntity<>(filterFood.getId(), HttpStatus.OK);
    }
    // 설문 결과 조회
    @GetMapping("/{userId}/surveyResult")
    public ResponseEntity<SurveyResultDto> surveyResult(@PathVariable Long userId){
        SurveyResultDto surveyResultDto = surveyService.surveyResult(userId);
        return new ResponseEntity<>(surveyResultDto, HttpStatus.OK);
    }

    // 설문조사 확인 api
    @GetMapping("/{userId}/checkingSurvey")
    public ResponseEntity<String> checkingSurvey(@PathVariable Long userId){
        if (surveyService.checkingSurvey(userId)){
            return new ResponseEntity<>("설문 내용 있음.",HttpStatus.OK);
        }
        return new ResponseEntity<>("설문 내용 없음.",HttpStatus.OK);
    }

}
