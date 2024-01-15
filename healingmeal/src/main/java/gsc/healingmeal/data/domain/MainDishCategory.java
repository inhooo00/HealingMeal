package gsc.healingmeal.data.domain;

import gsc.healingmeal.member.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MainDishCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String foodName;
    private String foodCategory;
    private String representativeFoodName;
    private String Kcal;
    private String protein;
    private String fat;
    private String carbohydrate;
    private String sugar;
    private String sodium;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    @Builder
    public MainDishCategory(String foodName, String foodCategory, String representativeFoodName, String Kcal, String protein, String fat, String carbohydrate, String sugar, String sodium) {
        this.foodName = foodName;
        this.foodCategory = foodCategory;
        this.representativeFoodName = representativeFoodName;
        this.Kcal = Kcal;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        this.sugar = sugar;
        this.sodium = sodium;
    }
}
