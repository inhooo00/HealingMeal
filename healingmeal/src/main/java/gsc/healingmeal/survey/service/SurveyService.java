package gsc.healingmeal.survey.service;

import gsc.healingmeal.member.domain.User;
import gsc.healingmeal.member.repository.UserRepository;
import gsc.healingmeal.survey.doamin.FilterFood;
import gsc.healingmeal.survey.doamin.Survey;
import gsc.healingmeal.survey.dto.FilterFoodRequestDto;
import gsc.healingmeal.survey.dto.SurveyRequestDto;
import gsc.healingmeal.survey.repository.FilterFoodRepository;
import gsc.healingmeal.survey.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final FilterFoodRepository filterFoodRepository;
    private final UserRepository userRepository;


    public SurveyService(SurveyRepository surveyRepository, FilterFoodRepository filterFoodRepository, UserRepository userRepository){
        this.surveyRepository = surveyRepository;
        this.filterFoodRepository = filterFoodRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Survey submitSurvey(SurveyRequestDto surveyRequestDto, Long userId){


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
        return survey;
    }
    @Transactional
    public void submitFilterFood(FilterFoodRequestDto filterFoodRequestDto ,Long surveyId){
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
    }
}
