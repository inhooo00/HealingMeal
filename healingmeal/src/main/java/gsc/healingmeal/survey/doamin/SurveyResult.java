package gsc.healingmeal.survey.doamin;

import gsc.healingmeal.member.domain.User;
import gsc.healingmeal.survey.dto.SurveyRequestDto;
import gsc.healingmeal.survey.dto.SurveyResultDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SurveyResult {
    @Id
    @Column(name = "surveyResult_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Kcal;
    private String protein;
    private String carbohydrate;
    private String fat;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 정적 팩토리 메서드
    public static SurveyResult createSurveyResult(String kcal, String protein, String fat, String carbohydrate, User user) {
        return SurveyResult.builder()
                .Kcal(kcal)
                .protein(protein)
                .fat(fat)
                .carbohydrate(carbohydrate)
                .user(user)
                .build();
    }

    public void update(SurveyResult surveyResult){
        this.Kcal = surveyResult.getKcal();
        this.protein = surveyResult.getProtein();
        this.carbohydrate = surveyResult.getCarbohydrate();
        this.fat = surveyResult.getFat();
    }
}
