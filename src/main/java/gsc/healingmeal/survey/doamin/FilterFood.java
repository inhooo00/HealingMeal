package gsc.healingmeal.survey.doamin;

import gsc.healingmeal.survey.dto.FilterFoodRequestDto;
import gsc.healingmeal.survey.dto.SurveyRequestDto;
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

    // 정적 팩토리 메서드
    public static FilterFood createFilterFood(FilterFoodRequestDto filterFoodRequestDto, Survey survey) {
        return FilterFood.builder()
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
                .survey(survey)
                .build();
    }
    public void update(FilterFoodRequestDto filterFoodRequestDto){
        this.stewsAndHotpots = filterFoodRequestDto.getStewsAndHotpots();
        this.steamedFood = filterFoodRequestDto.getSteamedFood();
        this.stirFriedFood = filterFoodRequestDto.getStirFriedFood();
        this.grilledFood = filterFoodRequestDto.getGrilledFood();
        this.vegetableFood = filterFoodRequestDto.getVegetableFood();
        this.stewedFood = filterFoodRequestDto.getStewedFood();
        this.pancakeFood = filterFoodRequestDto.getPancakeFood();
        this.breadAndConfectionery = filterFoodRequestDto.getBreadAndConfectionery();
        this.beveragesAndTeas = filterFoodRequestDto.getBeveragesAndTeas();
        this.dairyProducts = filterFoodRequestDto.getDairyProducts();
    }
}
