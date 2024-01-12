package gsc.healingmeal.survey.doamin;

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
public class FilterFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "filterfood_id")
    private Long id;

    private String stewsAndHotpots; // 찌개와 전골류

    private String stewedFood; // 조림류

    private String stirFriedFood; // 볶음류

    private String grilledFood; // 구이류

    private String vegetableFood; // 나물 숙채류

    private String steamedFood; // 찜류

    private String pancakeFood; // 전 적 및 부침류

    private String breadAndConfectionery; // 빵 및 과자류

    private String beveragesAndTeas; // 음료 및 차류

    private String dairyProducts; // 유제품류

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;
}
