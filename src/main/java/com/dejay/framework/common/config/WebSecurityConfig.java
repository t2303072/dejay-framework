package com.dejay.framework.common.config;

import com.dejay.framework.common.enums.AuthorityEnum;
import com.dejay.framework.common.filter.AuthorityFilter;
import com.dejay.framework.common.filter.JwtExceptionFilter;
import com.dejay.framework.common.utils.JwtUtil;
import com.dejay.framework.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security 7버전부터는 기존에 사용되던 다수의 메서드들이 deprecated 예정이라 해당 메서드는 미사용
 * @see <a href="https://docs.spring.io/spring-security/reference/migration-7/configuration.html">공식문서 참고</a>
 */

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final JwtExceptionFilter jwtExceptionFilter;
    private final JwtUtil jwtUtil;
    private final MemberService memberService;

    private final UserDetailsService userDetailsService;

    private static final String[] NO_AUTH_REQUIRED_URL = {"/index/**", "/test/**", "/member/sign-up", "/login", "/error"};
    private static final String[] AUTHORITY_REQUIRED_URL = {"/token/**", "/test/authorized-only"};
    private static final String[] AUTHENTICATION_REQUIRED_URL = {"/member/**"};
    private static final String[] AUTHORITY_LIST = {AuthorityEnum.DEVELOPMENT.getDeptCode(), AuthorityEnum.BUSINESS_SUPPORT.getDeptCode()};

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(ahr -> ahr
                        .requestMatchers("/static/**").permitAll()
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers(NO_AUTH_REQUIRED_URL).permitAll()
                        .requestMatchers(AUTHORITY_REQUIRED_URL).hasAnyAuthority(AUTHORITY_LIST)
                        .requestMatchers(AUTHENTICATION_REQUIRED_URL).authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(formLogin -> formLogin.disable())
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(hb -> hb.disable())
                .addFilterBefore(new AuthorityFilter(jwtUtil, memberService), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionFilter, AuthorityFilter.class)
                .logout(logout -> logout.permitAll()
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true))
                .headers(h -> h.frameOptions(Customizer.withDefaults()).disable())
        ;

        return httpSecurity.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
