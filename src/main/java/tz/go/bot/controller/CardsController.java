package tz.go.bot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

    @GetMapping("/cards")
    public String getCardDetails(String input) {
        return "Here are the cards details from the DB";
    }

}