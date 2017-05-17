package com.earo.test;

import com.earo.test.service.Impl.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by lauearo on 04/05/2017.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    UserDetailsService customeUserService(){
        return  new CustomUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customeUserService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        /*
        http.authorizeRequests()
                .antMatchers("*//**").permitAll()  //permit "/" and "/login" request
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")    //config logon path
                .defaultSuccessUrl("/chat").permitAll() //redirect path after login
                .and()
                .logout().permitAll();
         */

        http.authorizeRequests()
                .antMatchers("/api/**", "/publish").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login").failureUrl("/login?error").permitAll()
                .and()
                .logout().permitAll();

        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //super.configure(web);
        web.ignoring().regexMatchers(".*\\.css", ".*\\.js"); //permit static resources
    }
}
