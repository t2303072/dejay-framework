package com.dejay.framework.common.config;

import com.dejay.framework.common.filter.AdminAuthorityFilter;
import com.dejay.framework.common.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@Order(0)
//@RequiredArgsConstructor
//@Configuration
public class AdminSecurityConfig {

//    private final JwtUtil jwtUtil;
//
//    private final String[] permitPathArray = {"/admin/**"};
//
//    @Bean
//    protected SecurityFilterChain adminConfigure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .authorizeHttpRequests(ahr -> ahr
//                        .requestMatchers(permitPathArray).hasRole("ADMIN")
//                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
//                        .anyRequest().permitAll()
//                )
//                .formLogin(formLogin -> formLogin.disable())
//                .cors(cors -> cors.disable())
//                .csrf(csrf -> csrf.disable())
//                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .httpBasic(hb -> hb.disable())
//                .addFilterBefore(new AdminAuthorityFilter(jwtUtil, secretKey), UsernamePasswordAuthenticationFilter.class);
//
//        return httpSecurity.build();
//    }
}
