package tz.go.bot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import tz.go.bot.controller.NoticesController;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

//    /accounts --> Secured
//    /balance --> Secured
//    /loans --> Secured
//    /cards --> Secured
//    /notices --> Not Secured
//    /contact --> Not Secured

    @Override
    public void configure(HttpSecurity http) throws  Exception{
//        http
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .and()
//                .httpBasic();

        http
                .authorizeRequests()
                .antMatchers("/accounts").authenticated()
                .antMatchers("/balance").authenticated()
                .antMatchers("/cards").authenticated()
                .antMatchers("/loans").authenticated()
                .antMatchers("/notices").permitAll()
                .antMatchers("/contact").permitAll()
                .and()
                .formLogin()
                .and()
                .httpBasic();

    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{

        authenticationManagerBuilder
                .inMemoryAuthentication()
                .withUser("admin").password("123456").authorities("admin")
                .and()
                .withUser("user").password("654321").authorities("read")
                .and()
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

}
