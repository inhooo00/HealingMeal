package gsc.healingmeal.data.domain;

import gsc.healingmeal.survey.doamin.Survey;
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
    private String Kcal;
    private String protein;
    private String fat;
    private String carbohydrate;
    private String sugar;
    private String sodium;

    @OneToOne(mappedBy = "food",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MainDishCategory mainDishCategory;

    @OneToOne(mappedBy = "food",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private RiceCategory riceCategory;

    @OneToOne(mappedBy = "food",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SideDishCategory sideDishCategory;

    @OneToOne(mappedBy = "food",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SnackOrTeaCategory snackOrTeaCategory;
}