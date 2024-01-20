package gsc.healingmeal.member.dto;

import gsc.healingmeal.member.domain.Gender;
import gsc.healingmeal.member.domain.Role;
import gsc.healingmeal.member.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JoinRequestDto {
    private String loginId;

    private String password;

    private String name;

    private String email;

    private String birthDate;

    private Gender gender;

    private String phoneNumber;

    private Role role;

    // 암호화 사용 o
    public User toEntity(String encodedPassword){
        return User.builder()
                .loginId(this.loginId)
                .password(encodedPassword)
                .name(this.name)
                .email(this.email)
                .birthDate(this.birthDate)
                .gender(this.gender)
                .phoneNumber(phoneNumber)
                .role(this.role)
                .build();
    }
}
