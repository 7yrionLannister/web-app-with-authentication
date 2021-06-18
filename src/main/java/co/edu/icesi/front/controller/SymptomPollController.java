package co.edu.icesi.front.controller;

import co.edu.icesi.front.businessdelegate.BusinessDelgateI;
import co.edu.icesi.front.model.Symptompoll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("sympplls")
public class SymptomPollController {
    @Autowired
    private BusinessDelgateI businessDelgate;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("sympplls", businessDelgate.findAllSymptompoll());
        return "polls/index";
    }

    @GetMapping("/add")
    public String addSymptom(@ModelAttribute("symppll") Symptompoll symptompoll, Model model) {
        model.addAttribute("insts", businessDelgate.findAllInstitutions());
        return "polls/add-poll";
    }

    @PostMapping("/add")
    public String saveSymptom(@ModelAttribute("symppll") @Validated Symptompoll symptompoll, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action) {
        if(!action.equals("Cancel")) {
            if(result.hasErrors()) {
                model.addAttribute("insts", businessDelgate.findAllInstitutions());
                model.addAttribute("symppll", symptompoll);
                return "polls/add-poll";
            }
            businessDelgate.saveSymptompoll(symptompoll);
        }
        return "redirect:/sympplls/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("symppll", businessDelgate.findSymptompollById(id));
        model.addAttribute("insts", businessDelgate.findAllInstitutions());
        return "sympplls/update-poll";
    }

    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute("symppll") @Validated Symptompoll symptompoll, BindingResult result, Model model, String action) {
        if(!action.equals("Cancel")) {
            if(result.hasErrors()) {
                model.addAttribute("insts", businessDelgate.findAllInstitutions());
                return "polls/update-poll";
            }
            businessDelgate.updateSymptompoll(symptompoll);
        }
        return "redirect:/sympplls/";
    }

    @GetMapping("/del/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        Symptompoll symptompoll = businessDelgate.findSymptompollById(id);
        businessDelgate.deleteSymptompoll(symptompoll);
        return "redirect:/sympplls/";
    }
}
