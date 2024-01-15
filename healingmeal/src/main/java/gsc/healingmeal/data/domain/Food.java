package gsc.healingmeal.data.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food2_id")
    private Long id;

    private String foodName;
    private String foodCategory;
    private String representativeFoodName;
    private int Kcal;
    private float protein;
    private float fat;
    private float carbohydrate;
    private float sugar;
    private int sodium;
}