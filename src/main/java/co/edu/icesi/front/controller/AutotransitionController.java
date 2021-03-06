package co.edu.icesi.front.controller;

import co.edu.icesi.front.businessdelegate.BusinessDelegate;
import co.edu.icesi.front.model.Autotransition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/auts")
public class AutotransitionController implements AutotransitionControllerI {

	private ArrayList<String> logicalOperands;

	@Autowired
	private BusinessDelegate businessDelegate;

	//public AutotransitionController(AutotransitionService autService, AutotransitionRepositoryI autotransitionRepository, InstitutionService institutionService) { // Workshop2
	public AutotransitionController() { // Workshop3
		logicalOperands = new ArrayList<>();
		logicalOperands.add("AND");
		logicalOperands.add("OR");
		logicalOperands.add("XOR");
		logicalOperands.add("NOT");
		logicalOperands.add("NOR");
		logicalOperands.add("NAND");
	}

	@Override
	@GetMapping("/add")
	public String addAutotransition(Model model, @ModelAttribute("aut") Autotransition aut) {
		model.addAttribute("logicalOperands", logicalOperands);
		model.addAttribute("insts", businessDelegate.findAllInstitutions());
		return "/auts/add-aut";
	}

	@Override
	@GetMapping("/del/{id}")
	public String deleteAutotransition(@PathVariable("id") long id, Model model) {
		Autotransition aut = businessDelegate.findAutotransitionById(id);
		businessDelegate.deleteAutotransition(aut);
		return "redirect:/auts/";
	}

	@Override
	@GetMapping
	public String indexAutotransition(@RequestParam(required = false, value = "institution") Long institutionId, 
			@RequestParam(required = false, value = "id") Long id,
			@RequestParam(required = false, value = "name") String name,
			@RequestParam(required = false, value = "active") String active,
			@RequestParam(required = false, value = "logop") String logop,
			Model model) {
		if(id != null) {
			ArrayList<Autotransition> auts = new ArrayList<>();
			auts.add(businessDelegate.findAutotransitionById(id));
			model.addAttribute("auts", auts);
		} else if(institutionId != null) {
			//model.addAttribute("auts", autotransitionRepository.findAllByInstitution(institution)); // Workshop2
			model.addAttribute("auts", businessDelegate.findAllAutotransitionsByInstitutionInstId(institutionId)); // Workshop3
		} else if(name != null) {
			model.addAttribute("auts", businessDelegate.findAllAutotransitionsByName(name)); // Workshop3
		} else if(active != null) {
			model.addAttribute("auts", businessDelegate.findAllAutotransitionsByActive(active));
		} else if(logop != null) {
			model.addAttribute("auts", businessDelegate.findAllAutotransitionsByLogicalOperand(logop));
		} else {
			model.addAttribute("auts", businessDelegate.findAllAutotransitions());
		}
		return "/auts/index";
	}

	@Override
	@PostMapping("/add")
	public String saveAutotransition1(@ModelAttribute("aut") @Validated Autotransition aut, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if (result.hasErrors()) {
				model.addAttribute("aut", aut);
				model.addAttribute("logicalOperands", logicalOperands);
				model.addAttribute("insts", businessDelegate.findAllInstitutions());
				return "/auts/add-aut";
			}
			businessDelegate.saveAutotransition(aut);
		}
		return "redirect:/auts/";
	}

	@Override
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Autotransition aut = businessDelegate.findAutotransitionById(id);
		//u.setPassword(null);
		model.addAttribute("aut", aut);
		model.addAttribute("logicalOperands", logicalOperands);
		model.addAttribute("insts", businessDelegate.findAllInstitutions());
		return "/auts/update-aut";
	}

	@Override
	@PostMapping("/edit/{id}")
	public String updateAutotransition(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,
			@ModelAttribute("aut") @Validated Autotransition aut, BindingResult bindingResult, Model model) {
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("logicalOperands", logicalOperands);
				model.addAttribute("insts", businessDelegate.findAllInstitutions());
				return "auts/update-aut";
			}
			businessDelegate.editAutotransition(aut);
		}
		return "redirect:/auts/";
	}
}
