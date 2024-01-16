package gsc.healingmeal.menu.service;

import gsc.healingmeal.data.domain.MainDishCategory;
import gsc.healingmeal.data.domain.RiceCategory;
import gsc.healingmeal.data.domain.SideDishCategory;
import gsc.healingmeal.data.repository.MainDishCategoryRepository;
import gsc.healingmeal.data.repository.RiceCategoryRepository;
import gsc.healingmeal.data.repository.SideDishCategoryRepository;
import gsc.healingmeal.data.repository.SnackOrTeaCategoryRepository;
import gsc.healingmeal.member.domain.User;
import gsc.healingmeal.member.repository.UserRepository;
import gsc.healingmeal.menu.api.dto.MenuResponseDto;
import gsc.healingmeal.menu.domain.Meals;
import gsc.healingmeal.menu.domain.MenuForUser;
import gsc.healingmeal.menu.domain.repository.MenuRepository;
import gsc.healingmeal.survey.doamin.FilterFood;
import gsc.healingmeal.survey.doamin.Survey;
import gsc.healingmeal.survey.doamin.SurveyResult;
import gsc.healingmeal.survey.repository.FilterFoodRepository;
import gsc.healingmeal.survey.repository.SurveyRepository;
import gsc.healingmeal.survey.repository.SurveyResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuGeneraterForUser {

    private final UserRepository userRepository;

    //식단 저장할 repository
    private final MenuRepository menuRepository;

    //유저의 열탄단지 결과를 가져올 repository
    private final SurveyResultRepository surveyResultRepository;

    //유저의 설문조사 테이블을 경유해서 필터링 키워드를 가져올 repository
    private final SurveyRepository surveyRepository;

    //필터링 키워드 repository
    private final FilterFoodRepository filterFoodRepository;

    //대표메뉴
    private final MainDishCategoryRepository mainDishCategoryRepository;

    //반찬
    private final SideDishCategoryRepository sideDishCategoryRepository;

    //밥
    private final RiceCategoryRepository riceCategoryRepository;

    //간식
    private final SnackOrTeaCategoryRepository snackOrTeaCategoryRepository;

    @Value("${bucket-name}")
    private String bucket_name;


    //설문조사 결과(칼탄단지, 필터링 키워드)를 가지고 아침 간식 점심 간식 저녁
    //식단 생성(아침, 점심, 저녁만)

    public MenuResponseDto generateMenu(Meals meals, Long user_id){
        //식품 테이블에서 대표메뉴, 반찬, 밥을 랜덤하게 각각 가져옴. 단, 필터링을 적용함.
        //아래 코드는 난수를 생성하여 랜덤하게 식단을 가져오게 할 class
        SecureRandom secureRandom = new SecureRandom();

        /*
            대표메뉴
        */
        long recordCountForMain = mainDishCategoryRepository.count(); //row 수만큼의 랜덤값을 위한 long 변수.

        Survey survey = surveyRepository.findByUserId(user_id); //유저의 설문조사 번호를 찾기 위한 변수
        FilterFood filterFoodResponseDto = filterFoodRepository.findFilterFoodBySurveyId(survey.getId()); //유저의 필터링 내용 가져오기

        MainDishCategory mainDishCategory = mainDishCategoryRepository.findById(secureRandom.nextLong(recordCountForMain+1));

        //유저필터링 내용 저장
        //dto로 가져온 필터 키워드는 접미사로 -Keyword인 리스트에만 저장하고, ','를 제외한 List로 옮길 땐 접미사가 -List임.
        String[] mainDishFilterKeywords = {filterFoodResponseDto.getStewsAndHotpots(),filterFoodResponseDto.getGrilledFood(),filterFoodResponseDto.getGrilledFood(),filterFoodResponseDto.getPancakeFood()};

        //필터링 리스트를 하나로 배열.
        List<String> filterList = new ArrayList<>();
        for (String food : mainDishFilterKeywords) {
            filterList.addAll(Arrays.asList(food.split(",")));
        }

        //대표식품명이 필터링 키워드에 포함되지 않을 때까지 반복재생한다.
        while(filterList.contains(mainDishCategory.getRepresentativeFoodName())){
            mainDishCategory = mainDishCategoryRepository.findById(secureRandom.nextLong(recordCountForMain+1));
            if (mainDishCategory == null){
                mainDishCategory = mainDishCategoryRepository.findById(secureRandom.nextLong(recordCountForMain+1));
            }
        }

        /*
            밥
         */
        long recordCountForRice = riceCategoryRepository.count(); //row 수만큼의 랜덤값을 위한 long 변수

        RiceCategory riceCategory = riceCategoryRepository.findById(secureRandom.nextLong(recordCountForRice+1));
        if (riceCategory == null) { //rice가 null일 경우를 대비함.
            riceCategory = riceCategoryRepository.findById(secureRandom.nextLong(recordCountForRice+1));
        }

        /*
            반찬 2~3개
         */
        long recordCountForSide = sideDishCategoryRepository.count(); //row 수만큼의 랜덤값을 위한 long 변수
        int randomSideNumber = secureRandom.nextInt(2,4); //2~3개 중 몇 개의 반찬을 뽑을 것인지 정하는 변수
        int kcalSide = 0;
        float proteinSide = 0;
        float carbohydrateSide = 0;
        float fatSide = 0;

        //향후 결과값으로 반환할 땐 SideDishesDto 클래스로 할 것.
        //랜덤으로 정해진 반찬의 갯수만큼 반복
        List<SideDishCategory> sideDishCategories = new ArrayList<>();
        for (int start = 0; start < randomSideNumber; start++){
            sideDishCategories.add(sideDishCategoryRepository.findById(secureRandom.nextLong(recordCountForSide)));
            if (sideDishCategories.get(start) == null){
                sideDishCategories.set(start, sideDishCategoryRepository.findById(secureRandom.nextLong(recordCountForSide)));
            }
            kcalSide += sideDishCategories.get(start).getKcal();
            proteinSide += sideDishCategories.get(start).getProtein();
            carbohydrateSide += sideDishCategories.get(start).getCarbohydrate();
            fatSide += sideDishCategories.get(start).getFat();
        }
        //SideDishCateGory 객체로 리스트를 만들고 여기서 따로 반찬명 문자열 리스트 만들어서 필터 및 중복 검사를 하는데 편리하다고 생각하기 때문에 생성
        List<String> sideDishNames = new ArrayList<>();
        for (SideDishCategory name : sideDishCategories){
            sideDishNames.add(name.getRepresentativeFoodName());
        }

        //필터링 : 김치류, 볶음류, 나물 숙채류, 생채 무침류, 수조어육류, 장아찌 절임류, 젓갈류, 조림류
        String[] sideDishFilterKeywords = {filterFoodResponseDto.getVegetableFood(),filterFoodResponseDto.getStirFriedFood(),filterFoodResponseDto.getStewedFood()};
        List<String> sideDishFilterList = new ArrayList<>();
        for (String food : sideDishFilterKeywords) {
            sideDishFilterList.addAll(Arrays.asList(food.split(",")));
        }

        //필터 및 중복 검사
        for(int start = 0; start < sideDishCategories.size(); start++){
            //필터링
            while (sideDishFilterList.contains(sideDishNames.get(start)) && sideDishCategories.get(start) != null){
                //필터링 키워드에 가져온 식단명이 일치한다면(필터링에 저촉되면) 이에 포함되지 않는 새로운 식품을 찾아서 반환함.
                sideDishCategories.set(start, sideDishCategoryRepository.findById(secureRandom.nextLong(recordCountForSide)));
                //반찬의 음식명만을 모은 리스트도 이와 같이 갱신함.
                sideDishNames.set(start, sideDishCategories.get(start).getRepresentativeFoodName());
            }
            //중복 검사
            if (start>0){
                while (sideDishNames.contains(sideDishCategories.get(start).getRepresentativeFoodName())){
                    sideDishCategories.set(start, sideDishCategoryRepository.findById(secureRandom.nextLong(recordCountForSide)));
                }
                sideDishNames.set(start, sideDishCategories.get(start).getRepresentativeFoodName());
            }
        }

        /*
            열탄단지 합산
         */
        int kcal = mainDishCategory.getKcal() + riceCategory.getKcal() + kcalSide;
        float protein = mainDishCategory.getProtein() + riceCategory.getProtein() + proteinSide ;
        float carbohydrate = mainDishCategory.getCarbohydrate() + riceCategory.getCarbohydrate() + carbohydrateSide;
        float fat = mainDishCategory.getFat() + riceCategory.getFat() + fatSide;

        Optional<User> optionalUser = userRepository.findById(user_id);
        User user = optionalUser.get();

        //식단 반환
        return MenuResponseDto.builder()
                .main_dish(mainDishCategory.getRepresentativeFoodName())
                .imageURL("https://storage.googleapis.com/"+bucket_name+"/"+mainDishCategory.getRepresentativeFoodName()+".jpg")
                .sideDishForUserMenu(sideDishNames)
                .rice(riceCategory.getRepresentativeFoodName())
                .kcal(kcal)
                .protein(protein)
                .carbohydrate(carbohydrate)
                .fat(fat)
                .meals(meals)
                .user(user)
                .build();
    }

    public void save(MenuResponseDto menu){
        MenuForUser menuForUser = MenuForUser.builder()
                .main_dish(menu.getMain_dish())
                .imageURL("https://storage.googleapis.com/"+bucket_name+"/"+menu.getMain_dish()+".jpg")
                .rice(menu.getRice())
                .kcal(menu.getKcal())
                .protein(menu.getProtein())
                .carbohydrate(menu.getCarbohydrate())
                .fat(menu.getFat())
                .meals(menu.getMeals())
                .user(menu.getUser())
                .build();
        menuRepository.save(menuForUser);
    }

    //간식 생성
    //생성한 식단의 유효성을 검사하는 메소드
    public boolean examinMenu(Long user_id, MenuResponseDto morning,
                              MenuResponseDto MorningSnackOrTea,
                              MenuResponseDto lunch,
                              MenuResponseDto LunchSnackOrTea,
                              MenuResponseDto dinner){
        //유저의 설문조사 결과치를 가져옴. 이것은 식단 재생성에 대한 기준이 될 것. 해당 유저의 결과치를 초과하지 않도록 함.
        SurveyResult surveyResult = surveyResultRepository.findByUserId(user_id);
        int Kcal_result = surveyResult.getKcal();
        float protein_result = surveyResult.getProtein();
        float fat_result = surveyResult.getFat();
        float carborhydrate_result = surveyResult.getCarbohydrate();


        return true;
    }
}
