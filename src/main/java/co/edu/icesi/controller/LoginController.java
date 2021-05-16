package co.edu.icesi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import co.edu.icesi.model.Userr;

@Controller
public class LoginController {
	@GetMapping("/login")
	public String customLogin(Model model) {
		model.addAttribute("user", new Userr());
		return "custom-login";
	}
	
	@PostMapping("/login")
	public String login(@Validated @ModelAttribute("user") Userr user, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "login?error=true";
		}
		return "index";
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/access-denied")
	public String accessDeniedPage() {
		System.out.println("asdasda");
		return "denied";
	}
}
