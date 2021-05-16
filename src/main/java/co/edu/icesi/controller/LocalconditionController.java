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

import co.edu.icesi.model.Localcondition;
import co.edu.icesi.model.Precondition;
import co.edu.icesi.repository.LocalconditionRepositoryI;
import co.edu.icesi.service.LocalconditionService;
import co.edu.icesi.service.PreconditionService;
import co.edu.icesi.service.ThresholdService;


@Controller
@RequestMapping("locs")
public class LocalconditionController {

	private LocalconditionService localconditionService;
	private LocalconditionRepositoryI localconditionRepository;
	private PreconditionService preconditionService;
	private ThresholdService thresholdService;
	private ArrayList<String> operators;

	// @Autowired solo se necesita cuando hay varios constructores, ponerlo solo es costumbre
	public LocalconditionController(LocalconditionService localconditionService, LocalconditionRepositoryI localconditionRepository, PreconditionService preconditionService, ThresholdService thresholdService) {
		this.localconditionService = localconditionService;
		this.localconditionRepository = localconditionRepository;
		this.preconditionService = preconditionService;
		this.thresholdService = thresholdService;
		operators = new ArrayList<>();
		operators.add(">");
		operators.add(">=");
		operators.add("<");
		operators.add("<=");
		operators.add("<>");
	}

	@GetMapping
	public String index(@RequestParam(required = false, value = "precondition") Precondition precondition, Model model) {
		if(precondition == null) {
			model.addAttribute("locs", localconditionService.findAll());
		} else {
			model.addAttribute("locs", localconditionRepository.findAllByPrecondition(precondition));
		}
		return "locs/index";
	}

	@GetMapping("/add")
	public String addLocalconditionForm(Model model, @ModelAttribute("loc") Localcondition loc) {
		model.addAttribute("pres", preconditionService.findAll());
		model.addAttribute("thrs", thresholdService.findAll());
		model.addAttribute("ops", operators);
		return "locs/add-loc";
	}

	@PostMapping("/add")
	public String saveLocalcondition(@ModelAttribute("loc") @Validated Localcondition loc, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if (result.hasErrors()) {
				model.addAttribute("loc", loc);
				model.addAttribute("pres", preconditionService.findAll());
				model.addAttribute("thrs", thresholdService.findAll());
				model.addAttribute("ops", operators);
				return "locs/add-loc";
			}
			localconditionService.save(loc);
		}
		return "redirect:/locs/";
	}

	@GetMapping("/del/{id}")
	public String deleteLocalcondition(@PathVariable("id") long id, Model model) {
		Localcondition loc = localconditionService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid loc Id:" + id));
		localconditionService.delete(loc);
		return "redirect:/locs/";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Localcondition loc = localconditionService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid loc Id:" + id));
		model.addAttribute("loc", loc);
		model.addAttribute("pres", preconditionService.findAll());
		model.addAttribute("thrs", thresholdService.findAll());
		model.addAttribute("ops", operators);
		return "locs/update-loc";
	}

	@PostMapping("/edit/{id}")
	public String updateLocalcondition(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,
			@Validated Localcondition loc, BindingResult bindingResult, Model model) {
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("pres", preconditionService.findAll());
				model.addAttribute("thrs", thresholdService.findAll());
				model.addAttribute("ops", operators);
				return "locs/update-loc";
			}
			localconditionService.save(loc);
		}
		return "redirect:/locs/";
	}
}
