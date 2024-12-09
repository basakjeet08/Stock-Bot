package dev.anirban.stockbot.security;

import dev.anirban.stockbot.entity.Employee;
import dev.anirban.stockbot.util.UrlConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request
                                .requestMatchers(HttpMethod.POST, UrlConstants.LOGIN_EMPLOYEE).permitAll()

                                // Supplier Endpoints
                                .requestMatchers(HttpMethod.POST, UrlConstants.CREATE_SUPPLIER).hasAnyRole(
                                        Employee.EmployeeRole.OWNER.name(),
                                        Employee.EmployeeRole.MANAGER.name()
                                )
                                .requestMatchers(HttpMethod.PUT, UrlConstants.UPDATE_SUPPLIER).hasAnyRole(
                                        Employee.EmployeeRole.OWNER.name(),
                                        Employee.EmployeeRole.MANAGER.name()
                                )
                                .requestMatchers(HttpMethod.DELETE, UrlConstants.DELETE_SUPPLIER).hasAnyRole(
                                        Employee.EmployeeRole.OWNER.name(),
                                        Employee.EmployeeRole.MANAGER.name()
                                )

                                // Product Endpoints
                                .requestMatchers(HttpMethod.POST, UrlConstants.CREATE_PRODUCT).hasAnyRole(
                                        Employee.EmployeeRole.OWNER.name(),
                                        Employee.EmployeeRole.MANAGER.name()
                                )
                                .requestMatchers(HttpMethod.PUT, UrlConstants.UPDATE_PRODUCT).hasAnyRole(
                                        Employee.EmployeeRole.OWNER.name(),
                                        Employee.EmployeeRole.MANAGER.name()
                                )
                                .requestMatchers(HttpMethod.DELETE, UrlConstants.DELETE_PRODUCT).hasAnyRole(
                                        Employee.EmployeeRole.OWNER.name(),
                                        Employee.EmployeeRole.MANAGER.name()
                                )

                                // Restock Endpoints
                                .requestMatchers(HttpMethod.POST, UrlConstants.CREATE_RESTOCK).hasAnyRole(
                                        Employee.EmployeeRole.OWNER.name(),
                                        Employee.EmployeeRole.MANAGER.name(),
                                        Employee.EmployeeRole.STAFF.name()
                                )
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // Remove the "ROLE_" prefix
    }
}