package gsc.healingmeal.member.config;

import gsc.healingmeal.member.handler.CustomAuthenticationFailureHandler;
import gsc.healingmeal.member.handler.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends Exception {

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(c -> {
                    CorsConfigurationSource source = request -> {
                        var cors = new CorsConfiguration();
                        //배포 시 손봐야 함.
                        //허용할 origin
                        cors.setAllowedOrigins(List.of("http://localhost:5173"));
                        //허용할 method(CRUD)
                        cors.setAllowedMethods(List.of("*"));
                        //허용할 헤더
                        cors.setAllowedHeaders(List.of("*"));
                        cors.setAllowCredentials(true);
                        cors.setMaxAge(3000L);
                        return cors;
                    };
                    c.configurationSource(source);
                }
                )
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/**", "/user/**","/api/email-check").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement((session)-> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)//필요 시 세션 생성
                        .maximumSessions(1) // 동시 접속 가능 세션 오직 1
                        .maxSessionsPreventsLogin(false) //로그인 시도 시 현재 접속시도자 인증 실패
                        .expiredUrl("/main")
                )
              //form login 설정
                .formLogin((formLogin) ->
                        formLogin
                                .usernameParameter("loginId")
                                .passwordParameter("password")
                                .loginProcessingUrl("/user/login") //로그인 경로
                                .successHandler(customAuthenticationSuccessHandler) //로그인 성공 시 핸들러
                                .failureHandler(customAuthenticationFailureHandler)) //로그인 실패 시 핸들러
                                //.defaultSuccessUrl("/")) // 로그인 성공 시 리턴되는 위치
                .logout((logout)-> logout.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                        .logoutSuccessUrl("/successlogout") //로그아웃 성공 시 리턴되는 위치
                        .invalidateHttpSession(true)); // 로그아웃 시 인증정보 삭제 및 세션 무효화

        return http.build();
    }
    @Bean
        //스프링 시큐리티의 인증을 담당, 사용자 인증 시 UserService와 PasswordEncoder 사용
   public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
