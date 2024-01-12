package gsc.healingmeal.survey.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SurveyResultDto {
    private String Kcal;
    private String protein;
    private String carbohydrate;
    private String fat;

    public SurveyResultDto(String Kcal, String protein, String carbohydrate,String fat) {
        this.Kcal = Kcal;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
    }
}
