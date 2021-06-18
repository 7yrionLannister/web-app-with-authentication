package co.edu.icesi.front.controller;

import co.edu.icesi.front.businessdelegate.BusinessDelgateI;
import co.edu.icesi.front.model.Symptomquestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("sympqstns")
public class SymptomquestionController {

    @Autowired
    private BusinessDelgateI businessDelgate;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("sympqstns", businessDelgate.findAllSymptomquestions());
        return "questions/index";
    }

    @GetMapping("/add")
    public String addSymptomquestion(@ModelAttribute("symppll") Symptomquestion symptomquestion, Model model) {
        model.addAttribute("symps", businessDelgate.findAllSymptoms());
        model.addAttribute("polls", businessDelgate.findAllSymptompolls());
        return "questions/add-question";
    }

    @PostMapping("/add")
    public String saveSymptomquestion(@ModelAttribute("symppll") @Validated Symptomquestion symptomquestion, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action) {
        if(!action.equals("Cancel")) {
            if(result.hasErrors()) {
                model.addAttribute("insts", businessDelgate.findAllInstitutions());
                model.addAttribute("symppll", symptomquestion);
                return "questions/add-question";
            }
            businessDelgate.saveSymptomquestion(symptomquestion);
        }
        return "redirect:/sympqstns/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("symppll", businessDelgate.findSymptomquestionById(id));
        model.addAttribute("insts", businessDelgate.findAllInstitutions());
        return "sympqstns/update-question";
    }

    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute("symppll") @Validated Symptomquestion symptomquestion, BindingResult result, Model model, String action) {
        if(!action.equals("Cancel")) {
            if(result.hasErrors()) {
                model.addAttribute("insts", businessDelgate.findAllInstitutions());
                return "questions/update-question";
            }
            businessDelgate.updateSymptomquestion(symptomquestion);
        }
        return "redirect:/sympqstns/";
    }

    @GetMapping("/del/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        Symptomquestion symptomquestion = businessDelgate.findSymptomquestionById(id);
        businessDelgate.deleteSymptomquestion(symptomquestion);
        return "redirect:/sympqstns/";
    }
}
