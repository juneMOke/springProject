package org.o7planning.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;

import org.o7planning.thymeleaf.form.NumberForm;
import org.o7planning.thymeleaf.model.NumberResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    private static List<NumberResult> numberResults = new ArrayList<NumberResult>();

    static {
        NumberResult n2 = new NumberResult(5);
        NumberResult n3 = new NumberResult(36);
        NumberResult n4 = new NumberResult(142);
        n2.result();
        n3.result();
        n4.result();
        numberResults.add(n2);
        numberResults.add(n3);
        numberResults.add(n4);

    }

    // Injectez (inject) via application.properties.

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
        NumberForm numberForm = new NumberForm();
        model.addAttribute("numberForm", numberForm);
        model.addAttribute("numbers", numberResults);
        return "index";
    }
    @GetMapping(value="/indexError")
    public ResponseEntity<String> displayError(){
        return new ResponseEntity<String>("BAD REQUEST ERROR", HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = { "/registerNumber" }, method = RequestMethod.POST)
    public String savePerson(Model model,@ModelAttribute("numberForm") NumberForm numberForm) {

        if (numberForm.getNumberValue() <= -1 ) {
            return "redirect:/indexError";
        } else {
            NumberResult newNumberResult = new NumberResult(numberForm.getNumberValue());
            newNumberResult.result();
            numberResults.add(newNumberResult);
            return "redirect:/index";
        }

    }

}