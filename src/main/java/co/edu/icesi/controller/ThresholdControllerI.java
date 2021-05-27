package co.edu.icesi.controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.model.Institution;
import co.edu.icesi.model.Threshold;

public interface ThresholdControllerI {
	public String index(@RequestParam(required = false, value = "id") Long id,
			@RequestParam(required = false, value = "institution") Institution institution,
			@RequestParam(required = false, value = "name") String name,
			@RequestParam(required = false, value = "value") String value,
			@RequestParam(required = false, value = "type") String type,
			Model model);
	public String addThresholdForm(Model model, @ModelAttribute("thr") Threshold thr);
	public String saveThreshold(@ModelAttribute("thr") @Validated Threshold thr, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action);
	public String deleteThreshold(@PathVariable("id") long id, Model model);
	public String showUpdateForm(@PathVariable("id") long id, Model model);
	public String updateThreshold(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,
			@ModelAttribute("thr") @Validated Threshold thr, BindingResult bindingResult, Model model);
	
}
