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

import co.edu.icesi.front.model.Institution;
import co.edu.icesi.front.model.Threshold;


@Controller
@RequestMapping("thrs")
public class ThresholdController implements ThresholdControllerI {

	private BusinessDelgateI bd;

	// @Autowired solo se necesita cuando hay varios constructores, ponerlo solo es costumbre

	public ThresholdController(BusinessDelgateI bd) {
		this.bd	= bd;
	}

	@Override
	@GetMapping
	public String index(@RequestParam(required = false, value = "id") Long id,
			@RequestParam(required = false, value = "institution") Institution institution,
			@RequestParam(required = false, value = "name") String name,
			@RequestParam(required = false, value = "value") String value,
			@RequestParam(required = false, value = "type") String type,
			Model model) {
		if(id != null) {
			ArrayList<Threshold> thrs = new ArrayList<>();
			thrs.add(bd.getThreshold(id));
			model.addAttribute("thrs", thrs);
		} else if(institution != null) {

			model.addAttribute("thrs", bd.threshold_findAllByInstitution(institution.getInstId())); // Workshop3
		} else if(name != null) {
			model.addAttribute("thrs", bd.threshold_findAllByName(name));
		} else if(value != null) {
			model.addAttribute("thrs", bd.threshold_findAllByValue(value));
		} else if(type != null) {
			model.addAttribute("thrs", bd.threshold_findAllByType(type));
		} else {
			model.addAttribute("thrs", bd.threshold_findAll());
		}
		return "thrs/index";
	}

	@Override
	@GetMapping("/add")
	public String addThresholdForm(Model model, @ModelAttribute("thr") Threshold thr) {
		model.addAttribute("insts", bd.institution_findAll());
		return "thrs/add-thr";
	}

	@Override
	@PostMapping("/add")
	public String saveThreshold(@ModelAttribute("thr") @Validated Threshold thr, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if (result.hasErrors()) {
				model.addAttribute("thr", thr);
				model.addAttribute("insts", bd.institution_findAll());
				return "thrs/add-thr";
			}
			bd.threshold_save(thr);
		}
		return "redirect:/thrs/";
	}

	@Override
	@GetMapping("/del/{id}")
	public String deleteThreshold(@PathVariable("id") long id, Model model) {
		Threshold thr = bd.threshold_findById(id);
		bd.threshold_delete(thr);
		return "redirect:/thrs/";
	}

	@Override
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Threshold thr = bd.threshold_findById(id);
		model.addAttribute("thr", thr);
		model.addAttribute("insts", bd.institution_findAll());
		return "thrs/update-thr";
	}

	@Override
	@PostMapping("/edit/{id}")
	public String updateThreshold(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,
			@ModelAttribute("thr") @Validated Threshold thr, BindingResult bindingResult, Model model) {
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("insts", bd.institution_findAll());
				return "thrs/update-thr";
			}
			bd.editThreshold(thr);
		}
		return "redirect:/thrs/";
	}
}
