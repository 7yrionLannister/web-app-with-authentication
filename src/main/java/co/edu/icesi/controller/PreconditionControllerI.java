package co.edu.icesi.controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.model.Autotransition;
import co.edu.icesi.model.Precondition;

public interface PreconditionControllerI {
	public String index(@RequestParam(required = false, value = "id") Long id, 
			@RequestParam(required = false, value = "autotransition") Autotransition autotransition, 
			Model model);
	public String addPreconditionForm(Model model, @ModelAttribute("pre") Precondition pre);
	public String savePrecondition(@ModelAttribute("pre") @Validated Precondition pre, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action);
	public String deletePrecondition(@PathVariable("id") long id, Model model);
	public String showUpdateForm(@PathVariable("id") long id, Model model);
	public String updatePrecondition(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,
			@ModelAttribute("pre") @Validated Precondition pre, BindingResult bindingResult, Model model);
}
