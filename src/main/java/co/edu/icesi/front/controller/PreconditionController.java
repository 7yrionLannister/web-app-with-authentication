package co.edu.icesi.front.controller;

import java.util.ArrayList;

import co.edu.icesi.front.businessdelegate.BusinessDelgateI;
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

import co.edu.icesi.front.model.Precondition;

@Controller
@RequestMapping("pres")
public class PreconditionController implements PreconditionControllerI {

	private BusinessDelgateI bd;

	private ArrayList<String> logicalOperands;

	// @Autowired solo se necesita cuando hay varios constructores, ponerlo solo es costumbre

	public PreconditionController(BusinessDelgateI bd) { // Workshop3
		this.bd = bd;
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
			@RequestParam(required = false, value = "autotransition") Long autotransition,
			@RequestParam(required = false, value = "complicated-query") String complicatedQuery,
			Model model) {
		if(id != null) {
			ArrayList<Precondition> pres = new ArrayList<>();
			//pres.add(preconditionRepository.findById(id).get()); // Workshop2
			pres.add(bd.precondition_get(id)); // Workshop3
			model.addAttribute("pres", pres);
		} else if(autotransition != null) {
			//model.addAttribute("pres", preconditionRepository.findAllByAutotransition(autotransitionService.findById(autotransition).get().getAutotranId())); // Workshop2
			model.addAttribute("pres", bd.precondition_findAllByAutotransition(autotransition)); // Workshop3
		} if(complicatedQuery != null) {
			model.addAttribute("pres", bd.precondition_findAllWithAtLeastTwoLocalconditionsWithAThresholdWithValueGreatherThanOne());
		} else {
			model.addAttribute("pres", bd.precondition_findAll());
		}
		return "pres/index";
	}

	@Override
	@GetMapping("/add")
	public String addPreconditionForm(Model model, @ModelAttribute("pre") Precondition pre) {
		model.addAttribute("auts", bd.findAllAutotransitions());
		model.addAttribute("logicalOperands", logicalOperands);
		return "pres/add-pre";
	}

	@Override
	@PostMapping("/add")
	public String savePrecondition(@ModelAttribute("pre") @Validated Precondition pre, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action, @RequestParam(value="autotransition" , required = true) Long autId) {
		if (!action.equals("Cancel")) {
			if (result.hasErrors()) {
				model.addAttribute("pre", pre);
				model.addAttribute("auts", bd.findAllAutotransitions());
				model.addAttribute("logicalOperands", logicalOperands);
				return "pres/add-pre";
			}
			bd.precondition_save(pre);
		}
		return "redirect:/pres/";
	}

	@Override
	@GetMapping("/del/{id}")
	public String deletePrecondition(@PathVariable("id") long id, Model model) {
		Precondition pre = bd.precondition_findById(id);
		bd.precondition_delete(pre);
		return "redirect:/pres/";
	}

	@Override
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Precondition pre = bd.precondition_findById(id);
		model.addAttribute("pre", pre);
		model.addAttribute("auts", bd.findAllAutotransitions());
		model.addAttribute("logicalOperands", logicalOperands);
		return "pres/update-pre";
	}

	@Override
	@PostMapping("/edit/{id}")
	public String updatePrecondition(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,
			@ModelAttribute("pre") @Validated Precondition pre, BindingResult bindingResult, Model model) {
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("auts", bd.findAllAutotransitions());
				model.addAttribute("logicalOperands", logicalOperands);
				return "pres/update-pre";
			}
			bd.editPrecondition(pre);
		}
		return "redirect:/pres/";
	}
}
