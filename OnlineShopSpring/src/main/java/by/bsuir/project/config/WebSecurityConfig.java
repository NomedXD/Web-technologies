package by.bsuir.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((request) -> request
                                .requestMatchers(new AntPathRequestMatcher("/account/**"),
                                        new AntPathRequestMatcher("/cart/checkout"))
                                .authenticated()
                                .requestMatchers(new AntPathRequestMatcher("/**/import/**"),
                                        new AntPathRequestMatcher("/category/export/**"),
                                        new AntPathRequestMatcher("/admin/**"),
                                        new AntPathRequestMatcher("/catalog/export/**"))
                                .hasAuthority("ADMIN")
                                .anyRequest().permitAll()
                ).formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/catalog?page=1&size=5")
                        .usernameParameter("mail")
                        .passwordParameter("password").permitAll()
                ).logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/catalog?page=1&size=5")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
