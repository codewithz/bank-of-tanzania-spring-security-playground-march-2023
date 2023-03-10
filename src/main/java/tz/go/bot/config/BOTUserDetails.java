package tz.go.bot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tz.go.bot.model.BankCustomer;
import tz.go.bot.model.SecurityBankCustomer;
import tz.go.bot.repository.BankCustomerRepository;

@Service
public class BOTUserDetails implements UserDetailsService {

    private BankCustomerRepository bankCustomerRepository;

    @Autowired
    public void setBankCustomerRepository(BankCustomerRepository bankCustomerRepository) {
        this.bankCustomerRepository = bankCustomerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BankCustomer bankCustomer=bankCustomerRepository.findBankCustomerByEmail(username);

        if(bankCustomer!=null){
            SecurityBankCustomer securityBankCustomer=new SecurityBankCustomer(bankCustomer);
            return  securityBankCustomer;
        }
        else{
            throw new UsernameNotFoundException("User Details Not Found for "+username);
        }
    }
}
