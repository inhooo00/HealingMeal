package gsc.healingmeal.survey.doamin;

import gsc.healingmeal.member.domain.Gender;
import gsc.healingmeal.member.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survey_id")
    private Long id;

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

    @OneToOne(mappedBy = "survey",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FilterFood filterFood;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}
