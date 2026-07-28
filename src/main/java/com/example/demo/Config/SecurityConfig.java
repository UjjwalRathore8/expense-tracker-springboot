////
////package com.example.demo.Config;
////
////import com.example.demo.security.JwtFilter;
////
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.http.SessionCreationPolicy;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.security.web.SecurityFilterChain;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////
////@Configuration
////public class SecurityConfig {
////
////    private final JwtFilter jwtFilter;
////
////    public SecurityConfig(JwtFilter jwtFilter) {
////        this.jwtFilter = jwtFilter;
////    }
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http)
////            throws Exception {
////
////        http
////            .csrf(csrf -> csrf.disable())
////
////            .sessionManagement(session ->
////                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////            )
////
////            .authorizeHttpRequests(auth -> auth
//////                 public APIs
////                .requestMatchers(
////
////                    "/html/login.html",
////                    "/html/register.html",
////                    "/",
////                    "/html/**",
////                    "/css/**",
////                    "/js/**",
////                    "/image/**",
////                    "/images/**",
////                    "/favicon.ico",
//////                    "/api/login",
////                    "/api/auth/**",
//////                    "/api/register",
////                    "/api/users"
////                ).permitAll()
////
////                // all other APIs need JWT token
////                .anyRequest().authenticated()
////            )
////
////            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
////
////        return http.build();
////    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////}
//
//package com.example.demo.Config;
//
//import com.example.demo.security.JwtFilter;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//public class SecurityConfig {
//
//    private final JwtFilter jwtFilter;
//
//    public SecurityConfig(JwtFilter jwtFilter) {
//        this.jwtFilter = jwtFilter;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http)
//            throws Exception {
//
//        http
//            .csrf(csrf -> csrf.disable())
//
//            .sessionManagement(session ->
//                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            )
//
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers(
//                    "/",
//                    "/**.html",
//                    "/css/**",
//                    "/js/**",
//                    "/images/**",
//                    "/image/**",
//
//                    // Public authentication APIs
//                    "/api/auth/**",
//                    "/api/login",
//                    "/api/register"
//                ).permitAll()
//
//                .anyRequest().authenticated()
//            )
//
//            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}

package com.example.demo.Config;

import com.example.demo.security.JwtFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .authorizeHttpRequests(auth -> auth

                // Public pages
                .requestMatchers(
                    "/",
                    "/html/**",
                    "/css/**",
                    "/js/**",
                    "/images/**",
                    "/image/**",
                    "/favicon.ico"
                ).permitAll()


                // Public APIs
                .requestMatchers(
                    "/api/login",
                    "/api/users"
                ).permitAll()


                // Everything else requires JWT
                .anyRequest().authenticated()
            )

            .addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

