package tz.go.bot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tz.go.bot.model.BankCustomer;
import tz.go.bot.repository.BankCustomerRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class BOTUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private BankCustomerRepository bankCustomerRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setBankCustomerRepository(BankCustomerRepository bankCustomerRepository) {
        this.bankCustomerRepository = bankCustomerRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        Business Logic
//        Username and Password
//        It can be Iris, OTP

        System.out.println("-------- Using custom Authentication Provider -----------");
        String username=authentication.getName();
        String password=authentication.getCredentials().toString();

        BankCustomer bankCustomer=bankCustomerRepository.findBankCustomerByEmail(username);

        if(bankCustomer!=null){
            if(passwordEncoder.matches(password, bankCustomer.getPassword())){
                List<GrantedAuthority> authorityList=new ArrayList<>();
                authorityList.add(new SimpleGrantedAuthority(bankCustomer.getRole()));
                return  new UsernamePasswordAuthenticationToken(username,password,authorityList);
            }
            else{
                throw new BadCredentialsException("Invalid Password");
            }
        }
        else{
            throw new BadCredentialsException("No User Registed with given username");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
       return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
