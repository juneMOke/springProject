package org.o7planning.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;

import org.o7planning.thymeleaf.form.NumberForm;
import org.o7planning.thymeleaf.model.NumberResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    private static List<NumberResult> numberResults = new ArrayList<NumberResult>();

    static {
        NumberResult n1 = new NumberResult(-5);
        NumberResult n2 = new NumberResult(5);
        NumberResult n3 = new NumberResult(36);
        NumberResult n4 = new NumberResult(142);
        n2.result();
        n3.result();
        n4.result();
        numberResults.add(n1);
        numberResults.add(n2);
        numberResults.add(n3);
        numberResults.add(n4);

    }

    // Injectez (inject) via application.properties.
    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", message);

        return "index";
    }

    @RequestMapping(value = { "/numberList" }, method = RequestMethod.GET)
    public String numberList(Model model) {

        model.addAttribute("numbers", numberResults);

        return "numberList";
    }

    @RequestMapping(value = { "/registerNumber" }, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {

        NumberForm numberForm = new NumberForm();
        model.addAttribute("numberForm", numberForm);

        return "registerNumber";
    }

    @RequestMapping(value = { "/registerNumber" }, method = RequestMethod.POST)
    public String savePerson(Model model, //
                             @ModelAttribute("numberForm") NumberForm numberForm) {

        int numberValue = numberForm.getNumberValue();
        if ( numberValue > 0) {
            NumberResult newNumberResult = new NumberResult(numberValue);
            newNumberResult.result();
            numberResults.add(newNumberResult);

            return "redirect:/numberList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "registerNumber";
    }

}