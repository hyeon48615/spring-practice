package net.fullstack.security3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                authorz->
                authorz.requestMatchers("/images/**", "/*.html").permitAll()
                .anyRequest().authenticated() )
                .formLogin(Customizer.withDefaults());
        SecurityFilterChain chain = http.build();
        chain.getFilters().forEach( System.out::println );
        return chain;
    }
}