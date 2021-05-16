package co.edu.icesi.controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.model.Institution;
import co.edu.icesi.model.Userr;

public interface UserrControllerI {
	public String addUser(Model model);
	public String deleteUser(@PathVariable("id") long id, Model model);
	public String indexUser(@RequestParam(required = false, value = "institution") Institution institution, Model model);
	public String saveUser(@ModelAttribute("user") @Validated Userr user, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action);
	public String showUpdateForm(@PathVariable("id") long id, Model model);
	public String updateUser(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,
            @ModelAttribute("user") @Validated Userr user, BindingResult bindingResult, Model model);
}
