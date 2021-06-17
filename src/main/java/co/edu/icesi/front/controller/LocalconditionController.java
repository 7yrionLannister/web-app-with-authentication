package co.edu.icesi.front.controller;

import java.util.ArrayList;

import co.edu.icesi.front.model.Localcondition;
import co.edu.icesi.front.businessdelegate.BusinessDelegate;
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
@RequestMapping("locs")
public class LocalconditionController implements LocalconditionControllerI {

	@Autowired
	private BusinessDelegate businessDelegate;

	private ArrayList<String> operators;

	// @Autowired solo se necesita cuando hay varios constructores, ponerlo solo es costumbre
	//public LocalconditionController(LocalconditionService localconditionService, LocalconditionRepositoryI localconditionRepository, PreconditionService preconditionService, ThresholdService thresholdService) { // Workshop2
	public LocalconditionController() { // Workshop3
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
			model.addAttribute("locs", businessDelegate.findAllLocalconditionsByThreshold(threshold)); // Workshop3
		} else if(precondition != null) {
			//model.addAttribute("locs", localconditionRepository.findAllByPrecondition(preconditionService.findById(precondition).get().getPreconId())); // Workshop2
			model.addAttribute("locs", businessDelegate.findAllLocalconditionsByPrecondition(precondition)); // Workshop3
		} else if(name != null) {
			model.addAttribute("locs", businessDelegate.findAllLocalconditionsByName(name));
		} else if(type != null) {
			model.addAttribute("locs", businessDelegate.findAllLocalconditionsByType(type));
		} else {
			model.addAttribute("locs", businessDelegate.findAllLocalconditions());
		}
		return "locs/index";
	}

	@Override
	@GetMapping("/add")
	public String addLocalconditionForm(Model model, @ModelAttribute("loc") Localcondition loc) {
		model.addAttribute("pres", businessDelegate.findAllPreconditions());
		model.addAttribute("thrs", businessDelegate.findAllThresholds());
		model.addAttribute("ops", operators);
		return "locs/add-loc";
	}

	@Override
	@PostMapping("/add")
	public String saveLocalcondition(@ModelAttribute("loc") @Validated Localcondition loc, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if (result.hasErrors()) {
				model.addAttribute("loc", loc);
				model.addAttribute("pres", businessDelegate.findAllPreconditions());
				model.addAttribute("thrs", businessDelegate.findAllThresholds());
				model.addAttribute("ops", operators);
				return "locs/add-loc";
			}
			businessDelegate.saveLocalcondition(loc);
		}
		return "redirect:/locs/";
	}

	@Override
	@GetMapping("/del/{id}")
	public String deleteLocalcondition(@PathVariable("id") long id, Model model) {
		Localcondition loc = businessDelegate.findLocalconditionById(id);
		businessDelegate.deleteLocalcondition(loc);
		return "redirect:/locs/";
	}

	@Override
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Localcondition loc = businessDelegate.findLocalconditionById(id);
		model.addAttribute("loc", loc);
		model.addAttribute("pres", businessDelegate.findAllPreconditions());
		model.addAttribute("thrs", businessDelegate.findAllThresholds());
		model.addAttribute("ops", operators);
		return "locs/update-loc";
	}

	@Override
	@PostMapping("/edit/{id}")
	public String updateLocalcondition(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,
									   @Validated Localcondition loc, BindingResult bindingResult, Model model) {
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("pres", businessDelegate.findAllPreconditions());
				model.addAttribute("thrs", businessDelegate.findAllThresholds());
				model.addAttribute("ops", operators);
				return "locs/update-loc";
			}
			businessDelegate.saveLocalcondition(loc);
		}
		return "redirect:/locs/";
	}
}
