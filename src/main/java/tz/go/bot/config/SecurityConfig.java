package tz.go.bot.config;

import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import tz.go.bot.controller.NoticesController;

import javax.sql.DataSource;

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

//    @Override
//    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
//
////        authenticationManagerBuilder
////                .inMemoryAuthentication()
////                .withUser("admin").password("123456").authorities("admin")
////                .and()
////                .withUser("user").password("654321").authorities("read")
////                .and()
////                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//
//        InMemoryUserDetailsManager userDetailsManager=new InMemoryUserDetailsManager();
//        UserDetails user1= User.withUsername("admin").password("123456").authorities("admin").build();
//        UserDetails user2= User.withUsername("user").password("654321").authorities("read").build();
//
//        userDetailsManager.createUser(user1);
//        userDetailsManager.createUser(user2);
//
//        authenticationManagerBuilder.userDetailsService(userDetailsManager);
//    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        return  new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
