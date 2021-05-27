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
import co.edu.icesi.model.Threshold;
//import co.edu.icesi.repository.LocalconditionRepositoryI; // Workshop2
import co.edu.icesi.daos.LocalconditionDao; // Workshop3
import co.edu.icesi.service.LocalconditionService;
import co.edu.icesi.service.PreconditionService;
import co.edu.icesi.service.ThresholdService;


@Controller
@RequestMapping("locs")
public class LocalconditionController implements LocalconditionControllerI {

	private LocalconditionService localconditionService;
	//private LocalconditionRepositoryI localconditionRepository; // Workshop2
	private LocalconditionDao localconditionDao; // Workshop3
	private PreconditionService preconditionService;
	private ThresholdService thresholdService;
	private ArrayList<String> operators;

	// @Autowired solo se necesita cuando hay varios constructores, ponerlo solo es costumbre
	//public LocalconditionController(LocalconditionService localconditionService, LocalconditionRepositoryI localconditionRepository, PreconditionService preconditionService, ThresholdService thresholdService) { // Workshop2
	public LocalconditionController(LocalconditionService localconditionService, LocalconditionDao localconditionDao, PreconditionService preconditionService, ThresholdService thresholdService) { // Workshop3
		this.localconditionService = localconditionService;
		//this.localconditionRepository = localconditionRepository; // Workshop2
		this.localconditionDao = localconditionDao; // Workshop3
		this.preconditionService = preconditionService;
		this.thresholdService = thresholdService;
		operators = new ArrayList<>();
		operators.add(">");
		operators.add(">=");
		operators.add("<");
		operators.add("<=");
		operators.add("<>");
	}
	
	@Override
	@GetMapping
	public String index(@RequestParam(required = false, value = "threshold") Long threshold,
			@RequestParam(required = false, value = "precondition") Long precondition,
			@RequestParam(required = false, value = "name") String name,
			@RequestParam(required = false, value = "type") String type,
			Model model) {
		if(threshold != null) {
			//model.addAttribute("locs", localconditionRepository.findAllByThreshold(thresholdService.findById(threshold).get().getThresId())); // Workshop2
			model.addAttribute("locs", localconditionDao.findAllByThreshold(thresholdService.findById(threshold).get().getThresId())); // Workshop3
		} else if(precondition != null) {
			//model.addAttribute("locs", localconditionRepository.findAllByPrecondition(preconditionService.findById(precondition).get().getPreconId())); // Workshop2
			model.addAttribute("locs", localconditionDao.findAllByPrecondition(preconditionService.findById(precondition).get().getPreconId())); // Workshop3
		} else if(name != null) {
			model.addAttribute("locs", localconditionDao.findAllByName(name));
		} else if(type != null) {
			model.addAttribute("locs", localconditionDao.findAllByType(type));
		} else {
			model.addAttribute("locs", localconditionService.findAll());
		}
		return "locs/index";
	}

	@Override
	@GetMapping("/add")
	public String addLocalconditionForm(Model model, @ModelAttribute("loc") Localcondition loc) {
		model.addAttribute("pres", preconditionService.findAll());
		model.addAttribute("thrs", thresholdService.findAll());
		model.addAttribute("ops", operators);
		return "locs/add-loc";
	}

	@Override
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

	@Override
	@GetMapping("/del/{id}")
	public String deleteLocalcondition(@PathVariable("id") long id, Model model) {
		Localcondition loc = localconditionService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid loc Id:" + id));
		localconditionService.delete(loc);
		return "redirect:/locs/";
	}

	@Override
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Localcondition loc = localconditionService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid loc Id:" + id));
		model.addAttribute("loc", loc);
		model.addAttribute("pres", preconditionService.findAll());
		model.addAttribute("thrs", thresholdService.findAll());
		model.addAttribute("ops", operators);
		return "locs/update-loc";
	}

	@Override
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
