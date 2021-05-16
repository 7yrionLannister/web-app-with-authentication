package co.edu.icesi.controller;

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

import co.edu.icesi.model.Institution;
import co.edu.icesi.model.Threshold;
import co.edu.icesi.repository.ThresholdRepositoryI;
import co.edu.icesi.service.InstitutionService;
import co.edu.icesi.service.ThresholdService;


@Controller
@RequestMapping("thrs")
public class ThresholdController {

	private ThresholdService thresholdService;
	private ThresholdRepositoryI thresholdRepository;
	private InstitutionService institutionService;

	// @Autowired solo se necesita cuando hay varios constructores, ponerlo solo es costumbre
	public ThresholdController(ThresholdService thresholdService, ThresholdRepositoryI thresholdRepository, InstitutionService institutionService) {
		this.thresholdService = thresholdService;
		this.institutionService = institutionService;
		this.thresholdRepository = thresholdRepository;
	}

	@GetMapping
	public String index(@RequestParam(required = false, value = "institution") Institution institution, Model model) {
		if(institution == null) {
			model.addAttribute("thrs", thresholdService.findAll());
		} else {
			model.addAttribute("thrs", thresholdRepository.findAllByInstitution(institution));
		}
		return "thrs/index";
	}

	@GetMapping("/add")
	public String addThresholdForm(Model model, @ModelAttribute("thr") Threshold thr) {
		model.addAttribute("insts", institutionService.findAll());
		return "thrs/add-thr";
	}

	@PostMapping("/add")
	public String saveThreshold(@ModelAttribute("thr") @Validated Threshold thr, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if (result.hasErrors()) {
				model.addAttribute("thr", thr);
				model.addAttribute("insts", institutionService.findAll());
				return "thrs/add-thr";
			}
			thresholdService.save(thr);
		}
		return "redirect:/thrs/";
	}

	@GetMapping("/del/{id}")
	public String deleteThreshold(@PathVariable("id") long id, Model model) {
		Threshold thr = thresholdService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid thr Id:" + id));
		thresholdService.delete(thr);
		return "redirect:/thrs/";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Threshold thr = thresholdService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid thr Id:" + id));
		model.addAttribute("thr", thr);
		model.addAttribute("insts", institutionService.findAll());
		return "thrs/update-thr";
	}

	@PostMapping("/edit/{id}")
	public String updateThreshold(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,
			@ModelAttribute("thr") @Validated Threshold thr, BindingResult bindingResult, Model model) {
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("insts", institutionService.findAll());
				return "thrs/update-thr";
			}
			thresholdService.save(thr);
		}
		return "redirect:/thrs/";
	}
}
