package tz.go.bot.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import java.io.IOException;
import java.util.logging.Logger;

public class AuthoritiesLoggingAfterFilter implements Filter {

    private final Logger LOG=Logger.getLogger(AuthoritiesLoggingAfterFilter.class.getName());

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        System.out.println("------ After Filter Invoked-------------");
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        if(authentication!=null){
            LOG.info("User "+authentication.getName()+" is successfully authenticated" +
                    " and has authorities "+authentication.getAuthorities().toString());
            System.out.println("User "+authentication.getName()+" is successfully authenticated" +
                    " and has authorities "+authentication.getAuthorities().toString());

        }
        else{
            System.out.println("Authentication is Null");
        }
        chain.doFilter(request,response);
    }
}
