package co.edu.icesi.controller;

import java.util.ArrayList;

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

import co.edu.icesi.model.Autotransition;
import co.edu.icesi.model.Precondition;
import co.edu.icesi.repository.PreconditionRepositoryI;
import co.edu.icesi.service.AutotransitionService;
import co.edu.icesi.service.PreconditionService;


@Controller
@RequestMapping("pres")
public class PreconditionController implements PreconditionControllerI {

	private PreconditionService preconditionService;
	private PreconditionRepositoryI preconditionRepository;
	private AutotransitionService autotransitionService;
	private ArrayList<String> logicalOperands;

	// @Autowired solo se necesita cuando hay varios constructores, ponerlo solo es costumbre
	public PreconditionController(PreconditionService preconditionService, PreconditionRepositoryI preconditionRepository, AutotransitionService autotransitionService) {
		this.preconditionService = preconditionService;
		this.autotransitionService = autotransitionService;
		this.preconditionRepository = preconditionRepository;
		logicalOperands = new ArrayList<>();
		logicalOperands.add("AND");
		logicalOperands.add("OR");
		logicalOperands.add("XOR");
		logicalOperands.add("NOT");
		logicalOperands.add("NOR");
		logicalOperands.add("NAND");
	}

	@Override
	@GetMapping
	public String index(@RequestParam(required = false, value = "id") Long id, 
			@RequestParam(required = false, value = "autotransition") Autotransition autotransition, 
			Model model) {
		if(id != null) {
			ArrayList<Precondition> pres = new ArrayList<>();
			pres.add(preconditionRepository.findById(id).get());
			model.addAttribute("pres", pres);
		} else if(autotransition != null) {
			model.addAttribute("pres", preconditionRepository.findAllByAutotransition(autotransition));
		} else {
			model.addAttribute("pres", preconditionService.findAll());
		}
		return "pres/index";
	}

	@Override
	@GetMapping("/add")
	public String addPreconditionForm(Model model, @ModelAttribute("pre") Precondition pre) {
		model.addAttribute("auts", autotransitionService.findAll());
		model.addAttribute("logicalOperands", logicalOperands);
		return "pres/add-pre";
	}

	@Override
	@PostMapping("/add")
	public String savePrecondition(@ModelAttribute("pre") @Validated Precondition pre, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if (result.hasErrors()) {
				model.addAttribute("pre", pre);
				model.addAttribute("auts", autotransitionService.findAll());
				model.addAttribute("logicalOperands", logicalOperands);
				return "pres/add-pre";
			}
			preconditionService.save(pre);
		}
		return "redirect:/pres/";
	}

	@Override
	@GetMapping("/del/{id}")
	public String deletePrecondition(@PathVariable("id") long id, Model model) {
		Precondition pre = preconditionService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid pre Id:" + id));
		preconditionService.delete(pre);
		return "redirect:/pres/";
	}

	@Override
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Precondition pre = preconditionService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid pre Id:" + id));
		model.addAttribute("pre", pre);
		model.addAttribute("auts", autotransitionService.findAll());
		model.addAttribute("logicalOperands", logicalOperands);
		return "pres/update-pre";
	}

	@Override
	@PostMapping("/edit/{id}")
	public String updatePrecondition(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,
			@ModelAttribute("pre") @Validated Precondition pre, BindingResult bindingResult, Model model) {
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("auts", autotransitionService.findAll());
				model.addAttribute("logicalOperands", logicalOperands);
				return "pres/update-pre";
			}
			preconditionService.save(pre);
		}
		return "redirect:/pres/";
	}
}
