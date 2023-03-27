package tz.go.bot.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import tz.go.bot.constants.SecurityConstants;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class JWTTokenGeneratorFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        if(authentication!=null){
            SecretKey key= Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
            String jwt= Jwts
                    .builder()
                    .setIssuer("Bank of Tanzania")
                    .setSubject("JWT Token for User Login")
                    .claim("authorities",populateAuthorities(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime()+300000))
                    .signWith(key).compact();

                response.setHeader(SecurityConstants.JWT_HEADER,jwt);
        }
         filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        return !request.getServletPath().equals("/login");
    }


    private String populateAuthorities(Collection<? extends GrantedAuthority> collection){
        Set<String> authorities=new HashSet<>();
        for(GrantedAuthority authority:collection){
            authorities.add(authority.getAuthority());
        }
        return  String.join(",",authorities);
    }
}
