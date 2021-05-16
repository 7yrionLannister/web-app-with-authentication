package co.edu.icesi.controller;

import co.edu.icesi.model.Institution;
import co.edu.icesi.model.UserType;
import co.edu.icesi.model.Userr;
import co.edu.icesi.repository.UserRepositoryI;
import co.edu.icesi.service.InstitutionService;
import co.edu.icesi.service.UserrService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("users")
public class UserrController implements UserrControllerI {

    private UserrService userService;
    private UserRepositoryI userRepository;
    private InstitutionService institutionService;
    
    @Autowired
    public UserrController(UserrService userService, UserRepositoryI userRepository, InstitutionService institutionService) {
        this.userService = userService;
        this.institutionService = institutionService;
        this.userRepository = userRepository;
    }
    
    @Override
    @GetMapping("/add")
    public String addUser(Model model) {
    	model.addAttribute("user", new Userr());
        model.addAttribute("insts", institutionService.findAll());
        model.addAttribute("types", UserType.values());
        return "users/add-user";
    }

    @Override
    @GetMapping("/del/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Userr user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userService.delete(user);
        return "redirect:/users/";
    }

    @Override
    @GetMapping
    public String indexUser(@RequestParam(required = false, value = "institution") Institution institution, Model model) {
    	if(institution == null) {
        model.addAttribute("users", userService.findAll());
    	} else {
    		model.addAttribute("users", userRepository.findAllByInstitution(institution));
    	}
        return "users/index";
    }

    @Override
    @PostMapping("/add")
    public String saveUser(@ModelAttribute("user") @Validated Userr user, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("Cancel")) {
            if (result.hasErrors()) {
            	model.addAttribute("user", user);
            	model.addAttribute("insts", institutionService.findAll());
            	model.addAttribute("types", UserType.values());
                return "users/add-user";
            }
            String pwd = user.getUserPassword();
            if(!pwd.startsWith("{noop}") || pwd.equals("{noop}")) { // Si no tiene el prefijo se lo ponemos. Si la contrasenia es {noop} igual se le pone el prefijo
            	user.setUserPassword("{noop}" + pwd);
            }
            userService.save(user);
        }
        return "redirect:/users/";
    }
    
    @Override
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Userr user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        model.addAttribute("insts", institutionService.findAll());
        model.addAttribute("types", UserType.values());
        return "users/update-user";
    }
    
    @Override
    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,
                             @ModelAttribute("user") @Validated Userr user, BindingResult bindingResult, Model model) {
        if (action != null && !action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
            	model.addAttribute("insts", institutionService.findAll());
            	model.addAttribute("types", UserType.values());
                return "users/update-user";
            }
            if(user.getUserPassword().isEmpty()){ // si la contrasenia fue dejada vacia se pone lo que hay en la base de datos
                user.setUserPassword(userService.findById(id).get().getUserPassword());
            }
            userService.save(user);
        }
        return "redirect:/users/";
    }
}
