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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import tz.go.bot.controller.NoticesController;
import tz.go.bot.filter.AuthoritiesLoggingAfterFilter;
import tz.go.bot.filter.RequestValidationBeforeFilter;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.Collections;

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
                .cors()
                .configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration configuration=new CorsConfiguration();
                        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                        configuration.setAllowedMethods(Collections.singletonList("*")); //GET,POST,PUT,DELETE
                        configuration.setAllowCredentials(true);
                        configuration.setAllowedHeaders(Collections.singletonList("*"));
                        configuration.setMaxAge(3600L);
                        return  configuration;
                    }
                })
                .and()
                .csrf()
                .ignoringAntMatchers("/contact")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/accounts").hasAuthority("READ")
                .antMatchers("/balance").hasAnyAuthority("READ","WRITE")
                .antMatchers("/cards").hasAuthority("WRITE")
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

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource){
//        return  new JdbcUserDetailsManager(dataSource);
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

}
