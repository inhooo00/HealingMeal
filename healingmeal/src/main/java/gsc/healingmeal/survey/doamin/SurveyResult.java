package gsc.healingmeal.survey.doamin;

import gsc.healingmeal.member.domain.User;
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
}
