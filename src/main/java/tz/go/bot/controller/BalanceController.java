package tz.go.bot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @GetMapping("/balance")
    public String getBalanceDetails(String input) {
        return "Here are the balance details from the DB";
    }

}