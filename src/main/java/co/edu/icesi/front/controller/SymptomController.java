package co.edu.icesi.front.controller;

import co.edu.icesi.front.model.Symptom;
import co.edu.icesi.front.businessdelegate.BusinessDelgateI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("symps")
public class SymptomController {

    @Autowired
    private BusinessDelgateI businessDelgate;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("symps", businessDelgate.findAllSymptom());
        return "symps/index";
    }

    @GetMapping("/add")
    public String addSymptom(@ModelAttribute("symp") Symptom symptom) {
        return "symps/add-symp";
    }

    @PostMapping("/add")
    public String saveSymptom(@ModelAttribute("symp") @Validated Symptom symptom, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action) {
        if(!action.equals("Cancel")) {
            if(result.hasErrors()) {
                model.addAttribute("symp", symptom);
                return "symps/add-symp";
            }
            businessDelgate.saveSymptom(symptom);
        }
        return "redirect:/symps/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("symp", businessDelgate.findSymptomById(id));
        return "symps/update-symp";
    }

    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute("symp") @Validated Symptom symptom, BindingResult result, Model model, String action) {
        if(!action.equals("Cancel")) {
            if(result.hasErrors()) {
                return "symps/update";
            }
            businessDelgate.updateSymptom(symptom);
        }
        return "redirect:/symps/";
    }

    @GetMapping("/del/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        Symptom symptom = businessDelgate.findSymptomById(id);
        businessDelgate.deleteSymptom(symptom);
        return "redirect:/symps/";
    }
}
