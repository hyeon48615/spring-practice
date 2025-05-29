package net.fullstack.security4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityCofing {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // HTTP 요청에 대한 보안 설정
                .authorizeHttpRequests(
                        authz->authz
                                // login 인증이 필요하지 않은 URL 설정
                                .requestMatchers("/login").permitAll()
                                // 그 밖의 요청에 대해서는 인증이 필요
                                .anyRequest().authenticated()
                )
                .csrf((csrf)->csrf.disable())   //CSRF 에 대한 제약 무시
                // 폼 기반의 로그인 설정
                .formLogin(
                        // 커스텀 로그인 UI 페이지의 URL 지정
                        form->form.loginPage("/login")
                                // 로그인 처리 URL 지정
                                .loginProcessingUrl("/authentication")
                                // 사용자 아이디(이름) 파라미터 지정
                                .usernameParameter("usernameInput")
                                // 비밀번호 파라미터 지정
                                .passwordParameter("passwordInput")
                                // 로그인 성공 후 이동할 URL 지정 --> redirect 로 이동
                                .defaultSuccessUrl("/", true)
                                // 로그인 실패시 이동할 URL 지정
                                .failureUrl("/login?error")
                )
                // 로그아웃 설정
                .logout(logout->
                        // 로그아웃 처리할 URL 지정
                        logout.logoutUrl("/logout")
                                // 로그아웃 성고시 이동할 URL 지정 --> redirect 로 이동
                                .logoutSuccessUrl("/login?logout")
                                // 로그아웃시 세션을 무효화
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                );
        return http.build();
    }
}