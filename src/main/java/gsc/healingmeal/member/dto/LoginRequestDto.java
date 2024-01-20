package gsc.healingmeal.member.dto;

import gsc.healingmeal.member.domain.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequestDto {

    private String loginId;
    private String password;
    private Role role;

}
