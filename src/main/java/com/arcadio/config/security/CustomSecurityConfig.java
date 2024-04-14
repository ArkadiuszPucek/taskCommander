package com.arcadio.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfig {
    private static final String SALES_ENGINEER_ROLE = "Sales Engineer";
    private static final String SALES_MANAGER_ROLE = "Sales Manager";
    private static final String DESIGNER_ROLE = "Designer";
    private static final String WAREHOUSE_MANAGER_ROLE = "Warehouse Manager";
    private static final String ASSEMBLY_MANAGER_ROLE = "Assembly Manager";
    private static final String COUNTRY_MANAGER_ROLE = "Country Manager";


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/images/**", "/styles/**", "/scripts/**").permitAll()
                        .requestMatchers("/admin/**").hasAnyRole(SALES_MANAGER_ROLE, COUNTRY_MANAGER_ROLE)
                        .requestMatchers("/master/**").hasRole(COUNTRY_MANAGER_ROLE)
                        .requestMatchers("/register/**").permitAll()
                        .requestMatchers("/library/**").hasAnyRole(COUNTRY_MANAGER_ROLE, SALES_MANAGER_ROLE, SALES_ENGINEER_ROLE)
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .successHandler(myAuthenticationSuccessHandler())
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout/**", HttpMethod.GET.name()))
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request,
                                                HttpServletResponse response,
                                                Authentication authentication) throws IOException, ServletException {
                if (authentication.isAuthenticated()) {
                    getRedirectStrategy().sendRedirect(request, response, "/");
                } else {
                    super.onAuthenticationSuccess(request, response, authentication);
                }
            }
        };
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(
                "/img/**",
                "/scripts/**",
                "/styles/**",
                "/error"
        );
    }
}
