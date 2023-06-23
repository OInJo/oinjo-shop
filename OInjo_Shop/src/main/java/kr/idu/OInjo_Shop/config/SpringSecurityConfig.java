package kr.idu.OInjo_Shop.config;


import kr.idu.OInjo_Shop.Role.Role;
import kr.idu.OInjo_Shop.service.Member.OAuthUserService;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity      //spring security 설정 활성화
@RequiredArgsConstructor
//SpringBoot 에서는 @EnableJpaRepositories가 자동설정이 돼서 생략해도 됨.
public class SpringSecurityConfig {

    private final OAuthUserService oAuthUserService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()    //URL별 권한 관리를 설정하는 시작 지점

                //antMachers(..): URL, Http method 별로 관리 가능
                ///**는 /css로 시작하는 URL들을 의미한다.
                ///*와 /**는 다르다. /css/a/b는 /**만 매핑되고 /*에는 매핑이 안 된다
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/profile","/member/**","/**").permitAll()    //전체에서 열람 가능
                .antMatchers("/api/**").hasRole(Role.USER.name())   //USER 권한을 가진 사람만 접근 가능

                //.anyRequest(): 설정된 값 외에 나머지 URL
                .anyRequest().authenticated()   //나머지 URL은 인증된 사용자에게만 허용(로그인한 사용자만)
                .and()  //authorizeRequests() 종료 지점
                .logout().logoutSuccessUrl("/") //로그아웃 후 이동 페이지 지정
                .and()
                .oauth2Login()  //OAuth2 로그인 설정 시작 지점
                .userInfoEndpoint() //OAuth2 로그인 후 사용자 정보를 가져올 때의 설정 담당
                .userService(oAuthUserService);  //로그인 성공 후 조치를 진행할 UserService 인터페이스 구현체 등록,
        //로그인 서버에서 정보를 가져오고 나서 추가로 진행하고자 하는 명시 가능

        return http.build();
    }
}
