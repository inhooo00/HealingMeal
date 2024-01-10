package gsc.healingmeal.survey.dto;

import gsc.healingmeal.member.domain.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyRequestDto {

    private Long age;

    private String destination; // 목표

    private Long diabetesType; // 당뇨유형

    private Long numberOfExercises; // 육체 활동 빈도

    private Long height;

    private Long weight;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Long standardWeight; // 표준 체중

    private Long bodyMassIndex; // 체질량지수

    private Long caloriesNeededPerDay; // 하루 필요 열량

    private String weightLevel; // 현재 체중 단계

}
