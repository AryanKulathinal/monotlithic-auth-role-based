package com.example.monolithic_spring_security_product_demo.config;

import com.example.monolithic_spring_security_product_demo.filter.JwtAuthFilter;
import com.example.monolithic_spring_security_product_demo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthFilter authFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
//        UserDetails admin = User.withUsername("Admin")
//                .password(encoder.encode("iamadmin"))
//                .roles("ADMIN")
//                .authorities("ADMIN")
//                .build();
//        UserDetails user = User.withUsername("user")
//                .password(encoder.encode("iamuser"))
//                .roles("USER")
//                .authorities("USER")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
        return new CustomUserDetailsService();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/h2-console/**","/api/validate")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/products")
                        .hasAuthority("ADMIN")
                        .anyRequest()
                        .authenticated())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .headers((headers) -> headers.frameOptions((frame) -> frame.sameOrigin()));
        return http.build();

    }
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth.requestMatchers("/employees/**").hasAuthority("ADMIN").
//                        anyRequest().permitAll())
//                .headers((headers) -> headers.frameOptions((frame) -> frame.sameOrigin()));
//        return http.build();
//    }


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth ->
//                        auth.requestMatchers("/swagger-ui/**", "/h2-console/**").permitAll()  // Allow access to these paths
//                                .anyRequest().authenticated()
//                )
//                .headers(headers -> headers
//                        .contentSecurityPolicy("frame-ancestors 'self'") // Adjust this as needed
//                        .and()
//                        .frameOptions().sameOrigin()  // Allow H2 console to be displayed in an iframe
//                )
//                .formLogin(Customizer.withDefaults())
//                .build();
//    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }



}

