package com.legalhelp.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(CustomUserDetailsService userDetailsService, JwtAuthenticationFilter jwtFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF for testing and APIs
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configure(http))

                // Authorize requests
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/register", "/user/login", "/error").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/ca-list/**").permitAll()
                        .requestMatchers("/user/lawyer-list/**").permitAll()
                        .requestMatchers("/user/consultant-list/**").permitAll()
                        .requestMatchers("/assignments/**").permitAll()
                        .requestMatchers("/ca").hasRole("CA")
                        .requestMatchers("/ca/**").permitAll()
                        .requestMatchers("/user/**").hasRole("USER")
                        .requestMatchers("/lawyer/**").hasRole("LAWYER")
                        .requestMatchers("/consultant/**").hasRole("CONSULTANT")
                        .requestMatchers("/documents/request/**").permitAll()
                        .requestMatchers("/documents/request/**").hasAnyRole("CA","LAWYER","CONSULTANT")
                        .requestMatchers("/documents/upload?**").hasRole("USER")
                        .requestMatchers("/documents/**").permitAll()
                        .requestMatchers("/uploads/**").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().authenticated())

                // Disable form login (for REST API)
                // .formLogin(form -> form.disable())
                //
                // // Disable basic auth
                // .httpBasic(basic -> basic.disable())

                // No session management (optional for JWT)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public WebMvcConfigurer corsConfigure() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173", "https://legalhelp.dev", "https://www.legalhelp.dev",
                                "http://159.89.171.144")
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

}