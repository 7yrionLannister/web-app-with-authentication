package co.edu.icesi.controller;

import co.edu.icesi.model.Autotransition;
import co.edu.icesi.model.Institution;
//import co.edu.icesi.repository.AutotransitionRepositoryI; // Workshop2
import co.edu.icesi.daos.AutotransitionDao; // Workshop3
import co.edu.icesi.service.AutotransitionService;
import co.edu.icesi.service.InstitutionService;

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

	private AutotransitionService autService;
	//private AutotransitionRepositoryI autotransitionRepository; // Workshop2
	private AutotransitionDao autotransitionDao; // Workshop3
	private InstitutionService institutionService;
	private ArrayList<String> logicalOperands;

	@Autowired
	//public AutotransitionController(AutotransitionService autService, AutotransitionRepositoryI autotransitionRepository, InstitutionService institutionService) { // Workshop2
	public AutotransitionController(AutotransitionService autService, AutotransitionDao autotransitionDao, InstitutionService institutionService) { // Workshop3
		this.autService = autService;
		this.institutionService = institutionService;
		//this.autotransitionRepository = autotransitionRepository; // Workshop2
		this.autotransitionDao = autotransitionDao; // Workshop3
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
		model.addAttribute("insts", institutionService.findAll());
		return "/auts/add-aut";
	}

	@Override
	@GetMapping("/del/{id}")
	public String deleteAutotransition(@PathVariable("id") long id, Model model) {
		Autotransition aut = autService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid aut Id:" + id));
		autService.delete(aut);
		return "redirect:/auts/";
	}

	@Override
	@GetMapping
	public String indexAutotransition(@RequestParam(required = false, value = "institution") Long institutionId, 
			@RequestParam(required = false, value = "id") Long id,
			@RequestParam(required = false, value = "name") String name,
			@RequestParam(required = false, value = "active") String active,
			Model model) {
		if(id != null) {
			ArrayList<Autotransition> auts = new ArrayList<>();
			auts.add(autService.findById(id).get());
			model.addAttribute("auts", auts);
		} else if(institutionId != null) {
			//model.addAttribute("auts", autotransitionRepository.findAllByInstitution(institution)); // Workshop2
			model.addAttribute("auts", autotransitionDao.findAllByInstitutionInstId(institutionId)); // Workshop3
		} else if(name != null) {
			model.addAttribute("auts", autotransitionDao.findAllByName(name)); // Workshop3
		} else if(active != null) {
			model.addAttribute("auts", autotransitionDao.findAllByActive(active));
		} else {
			model.addAttribute("auts", autService.findAll());
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
				model.addAttribute("insts", institutionService.findAll());
				return "/auts/add-aut";
			}
			autService.save(aut);
		}
		return "redirect:/auts/";
	}

	@Override
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Autotransition aut = autService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid aut Id:" + id));
		//u.setPassword(null);
		model.addAttribute("aut", aut);
		model.addAttribute("logicalOperands", logicalOperands);
		model.addAttribute("insts", institutionService.findAll());
		return "/auts/update-aut";
	}

	@Override
	@PostMapping("/edit/{id}")
	public String updateAutotransition(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,
			@ModelAttribute("aut") @Validated Autotransition aut, BindingResult bindingResult, Model model) {
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("logicalOperands", logicalOperands);
				model.addAttribute("insts", institutionService.findAll());
				return "auts/update-aut";
			}
			autService.save(aut);
		}
		return "redirect:/auts/";
	}
}
