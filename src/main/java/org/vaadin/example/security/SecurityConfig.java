package org.vaadin.example.security;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.vaadin.example.views.LoginView;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig extends VaadinWebSecurity {

    @Autowired
    private SecurityService securityService;

    @EnableWebSecurity
    @Configuration
    public class SecurityConfiguration extends VaadinWebSecurity {

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeHttpRequests(auth -> auth
                            //.requestMatchers("/swagger-ui/**", "/v3/**", "/swagger-resources/**", "/webjars/**").permitAll()
                            //.requestMatchers("/**").permitAll()
                            .requestMatchers("/VAADIN/**", "/favicon.ico", "/icons/**", "/images/**", "/frontend/**").permitAll()
                            .requestMatchers("/user/login", "/user/newPassword", "/user/register", "/user/resetPassword").permitAll()
                            .requestMatchers("/user/**").permitAll() //Obstara vadin
                            //.requestMatchers("/**").hasAuthority("USER")
                    );

            //nebo requestUtil.isFrameworkInternalRequest
            http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.ignoringRequestMatchers(securityService::isFrameworkInternalRequest));
            http.formLogin(AbstractHttpConfigurer::disable);
            super.configure(http);
            //setLoginView(http, LoginView.class);
        }
    }
}
