package tz.go.bot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tz.go.bot.model.BankCustomer;
import tz.go.bot.repository.BankCustomerRepository;

import java.security.Principal;

@RestController
public class LoginController {

    private BankCustomerRepository bankCustomerRepository;

    @Autowired
    public void setBankCustomerRepository(BankCustomerRepository bankCustomerRepository) {
        this.bankCustomerRepository = bankCustomerRepository;
    }

    @GetMapping("/login")
    public BankCustomer getCustomerDetailsAfterLogin(Principal user){
       BankCustomer bankCustomer= bankCustomerRepository.findBankCustomerByEmail(user.getName());
       return bankCustomer;
    }
}
