package tz.go.bot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class AccountController {

    @GetMapping("/accounts")
    public String getAccountDetails(String input) {
        return "Here are the account details from the DB";
    }

}
