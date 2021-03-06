package co.edu.icesi.front.controller;

import java.util.ArrayList;

import co.edu.icesi.front.businessdelegate.BusinessDelegate;
import co.edu.icesi.front.model.Institution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("insts")
public class InstitutionController implements InstitutionControllerI {

	@Autowired
	private BusinessDelegate businessDelegate;

	// @Autowired solo se necesita cuando hay varios constructores, ponerlo solo es costumbre
	public InstitutionController() {
	}

	@Override
	@GetMapping
	public String index(@RequestParam(required = false, value = "id") Long id, Model model) {
		if(id == null) {
			model.addAttribute("insts", businessDelegate.findAllInstitutions());
		} else {
			ArrayList<Institution> insts = new ArrayList<>();
			insts.add(businessDelegate.findInstitutionById(id));
			model.addAttribute("insts", insts);
		}
		return "insts/index";
	}

	@Override
	@GetMapping("/add")
	public String addInstitutionForm(Model model) {
		model.addAttribute("inst", new Institution());
		return "insts/add-inst";
	}

	@Override
	@PostMapping("/add")
	public String saveInstitution(@ModelAttribute("inst") @Validated Institution inst, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if (result.hasErrors()) {
				model.addAttribute("inst", inst);
				return "insts/add-inst";
			}
			businessDelegate.saveInstitution(inst);
		}
		return "redirect:/insts/";
	}

	@Override
	@GetMapping("/del/{id}")
	public String deleteInstitution(@PathVariable("id") long id, Model model) {
		Institution inst = businessDelegate.findInstitutionById(id);
		businessDelegate.deleteInstitution(inst);
		return "redirect:/insts/";
	}

	@Override
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Institution inst = businessDelegate.findInstitutionById(id);
		model.addAttribute("inst", inst);
		return "insts/update-inst";
	}

	@Override
	@PostMapping("/edit/{id}")
	public String updateInstitution(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,
			@ModelAttribute("inst") @Validated Institution inst, BindingResult bindingResult, Model model) {
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				return "insts/update-inst";
			}
			businessDelegate.editInstitution(inst);
		}
		return "redirect:/insts/";
	}
}
