package gsc.healingmeal.menu.api;

import gsc.healingmeal.menu.api.dto.MenuResponseDto;
import gsc.healingmeal.menu.domain.Meals;
import gsc.healingmeal.menu.service.MenuGeneraterForUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MenuContoller {
    private final MenuGeneraterForUser menuGeneraterForUser;

    @GetMapping("/{userId}/generate")
    public ResponseEntity<MenuResponseDto> menu(@PathVariable Long userId){
        MenuResponseDto menuResponseDto = menuGeneraterForUser.generateMenu(Meals.breakfast,userId);
        menuGeneraterForUser.save(menuResponseDto);
        return new ResponseEntity<>(menuResponseDto, HttpStatus.OK);
    }
}
