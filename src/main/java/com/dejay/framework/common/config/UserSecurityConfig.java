package com.dejay.framework.common.config;

import com.dejay.framework.common.filter.UserAuthorityFilter;
import com.dejay.framework.common.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security 7버전부터는 기존에 사용되던 다수의 메서드들이 deprecated 예정이라 해당 메서드는 미사용
 * @see <a href="https://docs.spring.io/spring-security/reference/migration-7/configuration.html">공식문서 참고</a>
 */

//@Order(1)
//@RequiredArgsConstructor
//@EnableWebSecurity
//@Configuration
public class UserSecurityConfig {

//    private final JwtUtil jwtUtil;
//
//    private final String[] permitPathArray = {"/member/**"};
//
//    @Bean
//    protected SecurityFilterChain userConfigure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .authorizeHttpRequests(ahr -> ahr
//                        .requestMatchers(permitPathArray).authenticated()
//                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
//                        .anyRequest().permitAll()
//                )
//                .formLogin(formLogin -> formLogin.disable())
//                .cors(cors -> cors.disable())
//                .csrf(csrf -> csrf.disable())
//                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .httpBasic(hb -> hb.disable())
//                .addFilterBefore(new UserAuthorityFilter(jwtUtil, secretKey), UsernamePasswordAuthenticationFilter.class);
//
//        return httpSecurity.build();
//    }

}
