package tz.go.bot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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

}
