package com.dd.alcohol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AlcoholController {

    @GetMapping("/alcohol")
    public String alcohol() {
        return "alcohol/alcohol";
    }

    @GetMapping("/soju")
    public String soju() {
        return "alcohol/soju";
    }

    @GetMapping("/beer")
    public String beer() {
        return "alcohol/beer";
    }

    @GetMapping("/cheers")
    public String cheers() {
        return "alcohol/cheers";
    }

    @GetMapping("/game")
    public String game() {
        return "alcohol/game";
    }

    @GetMapping("/recipe")
    public String recipe() {
        return "alcohol/recipe";
    }
}
