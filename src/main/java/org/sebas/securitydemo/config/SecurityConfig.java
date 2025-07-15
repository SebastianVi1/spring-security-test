package org.sebas.securitydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //Disable csrf
        http.csrf(customizer -> customizer.disable());


        //All requests must be authorized first
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());

        //Enable login form
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


//        return http.csrf(customizer -> customizer.disable()).
//                authorizeHttpRequests(request -> request.anyRequest().authenticated()).
//                httpBasic(Customizer.withDefaults()).
//                sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();

        return http.build();

    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        //Manage different users
//
//        UsersDetails user1 = User
//                .withDefaultPasswordEncoder()
//                .username("sebas")
//                .password("1234")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user1);
//
//    }

    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}
