package com.codeup.gonzaloblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.ThreadLocalRandom;

@Controller
class DiceController {
    @GetMapping("/roll-dice")
    public String showRollDice() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{guess}")
    public String rollDice(@PathVariable int guess, Model model) {
        String message;
        int randomNum1 = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        int randomNum2 = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        int randomNum3 = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        int randomNum4 = ThreadLocalRandom.current().nextInt(1, 6 + 1);

        if (guess == randomNum1 || guess == randomNum2 || guess == randomNum3 || guess == randomNum4){
            message = "Yay, you guessed correctly!";
        } else {
            message = "Sorry, guess again";
        }

        model.addAttribute("randomNumber1", randomNum1);
        model.addAttribute("randomNumber2", randomNum2);
        model.addAttribute("randomNumber3", randomNum3);
        model.addAttribute("randomNumber4", randomNum4);
        model.addAttribute("guess", guess);
        model.addAttribute("message", message);

        return "roll-results";
    }


}
