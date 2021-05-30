package co.edu.icesi.controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.model.Autotransition;
import co.edu.icesi.model.Institution;

public interface AutotransitionControllerI {
	public String addAutotransition(Model model, @ModelAttribute("aut") Autotransition aut);
	public String deleteAutotransition(@PathVariable("id") long id, Model model);
	public String indexAutotransition(@RequestParam(required = false, value = "institution") Long institution, 
			@RequestParam(required = false, value = "id") Long id,
			@RequestParam(required = false, value = "name") String name,
			@RequestParam(required = false, value = "active") String active,
			@RequestParam(required = false, value = "logop") String logop,
			Model model);
	public String saveAutotransition1(@ModelAttribute("aut") @Validated Autotransition aut, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action);
	public String showUpdateForm(@PathVariable("id") long id, Model model);
	public String updateAutotransition(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,
			@ModelAttribute("aut") @Validated Autotransition aut, BindingResult bindingResult, Model model);
}
