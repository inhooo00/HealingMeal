package gsc.healingmeal.survey.service;

import gsc.healingmeal.member.domain.User;
import gsc.healingmeal.member.repository.UserRepository;
import gsc.healingmeal.survey.doamin.FilterFood;
import gsc.healingmeal.survey.doamin.Survey;
import gsc.healingmeal.survey.doamin.SurveyResult;
import gsc.healingmeal.survey.dto.FilterFoodRequestDto;
import gsc.healingmeal.survey.dto.SurveyRequestDto;
import gsc.healingmeal.survey.dto.SurveyResultDto;
import gsc.healingmeal.survey.repository.FilterFoodRepository;
import gsc.healingmeal.survey.repository.SurveyRepository;
import gsc.healingmeal.survey.repository.SurveyResultRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final FilterFoodRepository filterFoodRepository;
    private final UserRepository userRepository;
    private final SurveyResultRepository surveyResultRepository;


    public SurveyService(SurveyRepository surveyRepository, FilterFoodRepository filterFoodRepository, UserRepository userRepository, SurveyResultRepository surveyResultRepository) {
        this.surveyRepository = surveyRepository;
        this.filterFoodRepository = filterFoodRepository;
        this.userRepository = userRepository;
        this.surveyResultRepository = surveyResultRepository;
    }

    @Transactional
    public Survey submitSurvey(SurveyRequestDto surveyRequestDto, Long userId) {


        Survey survey = Survey.builder()
                .age(surveyRequestDto.getAge())
                .destination(surveyRequestDto.getDestination())
                .diabetesType(surveyRequestDto.getDiabetesType())
                .numberOfExercises(surveyRequestDto.getNumberOfExercises())
                .height(surveyRequestDto.getHeight())
                .weight(surveyRequestDto.getWeight())
                .gender(surveyRequestDto.getGender())
                .standardWeight(surveyRequestDto.getStandardWeight())
                .bodyMassIndex(surveyRequestDto.getBodyMassIndex())
                .caloriesNeededPerDay(surveyRequestDto.getCaloriesNeededPerDay())
                .weightLevel(surveyRequestDto.getWeightLevel())
                .user(userRepository.findById(userId).orElseThrow())
                .build();
        surveyRepository.save(survey);

        SurveyResult surveyResult = SurveyResult.builder()
                .Kcal(survey.getCaloriesNeededPerDay().toString())
                .protein(proteinCalculation(survey.getCaloriesNeededPerDay().toString()).toString())
                .fat(fatCalculation(survey.getCaloriesNeededPerDay().toString()).toString())
                .carbohydrate(carbohydrateCalculation(survey.getCaloriesNeededPerDay().toString()).toString())
                .user(userRepository.findById(userId).orElseThrow())
                .build();
        surveyResultRepository.save(surveyResult);

        return survey;
    }

    @Transactional
    public FilterFood submitFilterFood(FilterFoodRequestDto filterFoodRequestDto, Long surveyId) {
        FilterFood filterFood = FilterFood.builder()
                .stewsAndHotpots(filterFoodRequestDto.getStewsAndHotpots())
                .steamedFood(filterFoodRequestDto.getSteamedFood())
                .stirFriedFood(filterFoodRequestDto.getStirFriedFood())
                .grilledFood(filterFoodRequestDto.getGrilledFood())
                .vegetableFood(filterFoodRequestDto.getVegetableFood())
                .stewedFood(filterFoodRequestDto.getStewedFood())
                .pancakeFood(filterFoodRequestDto.getPancakeFood())
                .breadAndConfectionery(filterFoodRequestDto.getBreadAndConfectionery())
                .beveragesAndTeas(filterFoodRequestDto.getBeveragesAndTeas())
                .dairyProducts(filterFoodRequestDto.getDairyProducts())
                .survey(surveyRepository.findById(surveyId).orElseThrow())
                .build();

        filterFoodRepository.save(filterFood);
        return filterFood;
    }

    // 설문 조사 결과
    public SurveyResultDto surveyResult(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        SurveyResult surveyResult = surveyResultRepository.findSurveyResultByUser(user);
        SurveyResultDto surveyResultDto = new SurveyResultDto(
                surveyResult.getKcal(),surveyResult.getProtein(),surveyResult.getCarbohydrate(), surveyResult.getFat());

        return surveyResultDto;
    }

    private Double proteinCalculation(String Kcal) {
        double result = Double.parseDouble(Kcal) * 13.5 / 400;
        return Math.round(result * 10) / 10.0;
    }

    private Double fatCalculation(String Kcal) {
        double result = Double.parseDouble(Kcal) * 20 / 900;
        return Math.round(result * 10) / 10.0;
    }

    private Double carbohydrateCalculation(String Kcal) {
        double result = Double.parseDouble(Kcal) * 62.5 / 400;
        return Math.round(result * 10) / 10.0;
    }
}
