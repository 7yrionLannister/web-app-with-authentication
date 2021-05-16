package co.edu.icesi.controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.model.Localcondition;
import co.edu.icesi.model.Precondition;
import co.edu.icesi.model.Threshold;

public interface LocalconditionControllerI {
	public String index(@RequestParam(required = false, value = "threshold") Threshold threshold,
			@RequestParam(required = false, value = "precondition") Precondition precondition,
			Model model);
	public String addLocalconditionForm(Model model, @ModelAttribute("loc") Localcondition loc);
	public String saveLocalcondition(@ModelAttribute("loc") @Validated Localcondition loc, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action);
	public String deleteLocalcondition(@PathVariable("id") long id, Model model);
	public String showUpdateForm(@PathVariable("id") long id, Model model);
	public String updateLocalcondition(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,
			@Validated Localcondition loc, BindingResult bindingResult, Model model);
}
