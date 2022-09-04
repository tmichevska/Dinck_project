package com.example.dnic2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(
            PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
//                .antMatchers("/").permitAll()
                .antMatchers("/login", "/**", "/css/**",
                        "/", "/tests", "/home/videos/**","/fonts/**", "/static/js/**",
                        "/videos/**", "/home","/contact","/blog","/course","/quiz",
                        "/home/**", "/assets/**", "/register", "/api/**", "/logout","/vendor/**","/static/vendor/**","/static/assets/img/**").permitAll()

                .antMatchers("/admin/**").hasRole("EMPLOYEE")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //.loginPage("/login").permitAll()
                .failureUrl("/login?error=BadCredentials")
                .defaultSuccessUrl("/home", true)
                .and()
                .logout()
                .logoutUrl("/logout")

                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/home")
                .and()
                .exceptionHandling().accessDeniedPage("/access_denied");

    }
}
