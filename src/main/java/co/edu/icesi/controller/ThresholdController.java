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

import co.edu.icesi.model.Institution;
import co.edu.icesi.model.Threshold;
import co.edu.icesi.repository.ThresholdRepositoryI;
import co.edu.icesi.service.InstitutionService;
import co.edu.icesi.service.ThresholdService;


@Controller
@RequestMapping("thrs")
public class ThresholdController implements ThresholdControllerI {

	private ThresholdService thresholdService;
	private ThresholdRepositoryI thresholdRepository;
	private InstitutionService institutionService;

	// @Autowired solo se necesita cuando hay varios constructores, ponerlo solo es costumbre
	public ThresholdController(ThresholdService thresholdService, ThresholdRepositoryI thresholdRepository, InstitutionService institutionService) {
		this.thresholdService = thresholdService;
		this.institutionService = institutionService;
		this.thresholdRepository = thresholdRepository;
	}

	@Override
	@GetMapping
	public String index(@RequestParam(required = false, value = "id") Long id, @RequestParam(required = false, value = "institution") Institution institution, Model model) {
		if(id != null) {
			ArrayList<Threshold> thrs = new ArrayList<>();
			thrs.add(thresholdRepository.findById(id).get());
			model.addAttribute("thrs", thrs);
		} else if(institution != null) {
			model.addAttribute("thrs", thresholdRepository.findAllByInstitution(institution));
		} else {
			model.addAttribute("thrs", thresholdService.findAll());
		}
		return "thrs/index";
	}

	@Override
	@GetMapping("/add")
	public String addThresholdForm(Model model, @ModelAttribute("thr") Threshold thr) {
		model.addAttribute("insts", institutionService.findAll());
		return "thrs/add-thr";
	}

	@Override
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

	@Override
	@GetMapping("/del/{id}")
	public String deleteThreshold(@PathVariable("id") long id, Model model) {
		Threshold thr = thresholdService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid thr Id:" + id));
		thresholdService.delete(thr);
		return "redirect:/thrs/";
	}

	@Override
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Threshold thr = thresholdService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid thr Id:" + id));
		model.addAttribute("thr", thr);
		model.addAttribute("insts", institutionService.findAll());
		return "thrs/update-thr";
	}

	@Override
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
