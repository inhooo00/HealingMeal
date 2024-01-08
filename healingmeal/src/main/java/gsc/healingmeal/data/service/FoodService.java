package gsc.healingmeal.data.service;


import gsc.healingmeal.data.domain.Food;
import gsc.healingmeal.data.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.Reader;

@RequiredArgsConstructor
@EnableScheduling
@Service
public class FoodService {

    private final FoodRepository foodRepository;

    @Transactional
    public void loadSave() throws Exception {
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("/Users/inho/Downloads/-20240105.json");
        JSONArray records = (JSONArray) parser.parse(reader);

        for (Object record : records) {
            try {

                JSONObject tmp = (JSONObject) record;
                Food food = Food.builder()
                        .foodName(tmp.get("식품명").toString())
                        .foodCategory(tmp.get("식품대분류명").toString())
                        .representativeFoodName(tmp.get("대표식품명").toString())
                        .Kcal(tmp.get("에너지(kcal)").toString())
                        .protein(tmp.get("단백질(g)").toString())
                        .fat(tmp.get("지방(g)").toString())
                        .carbohydrate(tmp.get("탄수화물(g)").toString())
                        .sugar(tmp.get("당류(g)").toString())
                        .sodium(tmp.get("나트륨(mg)").toString())
                        .build();

                foodRepository.save(food);
            } catch (Exception e) {
                System.err.println("ClassCastException 발생: " + e.getMessage());
            }
        }

    }

}
