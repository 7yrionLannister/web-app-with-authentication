package co.edu.icesi.front.controller;

import co.edu.icesi.front.model.Institution;
import co.edu.icesi.front.model.UserType;
import co.edu.icesi.front.model.Userr;

import co.edu.icesi.front.businessdelegate.BusinessDelgateI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("users")
public class UserrController implements UserrControllerI {

    private BusinessDelgateI bd;
    
    @Autowired
    public UserrController(BusinessDelgateI bd) {
        this.bd = bd;
    }
    
    @Override
    @GetMapping("/add")
    public String addUser(Model model) {
    	model.addAttribute("user", new Userr());
        model.addAttribute("insts", bd.institution_findAll());
        model.addAttribute("types", UserType.values());
        return "users/add-user";
    }

    @Override
    @GetMapping("/del/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Userr user = bd.user_findById(id);
        bd.user_delete(user);
        return "redirect:/users/";
    }

    @Override
    @GetMapping
    public String indexUser(@RequestParam(required = false, value = "institution") Institution institution, Model model) {
    	if(institution == null) {
        model.addAttribute("users", bd.user_findAll());
    	} else {
    		model.addAttribute("users", bd.user_findAllByInstitution(institution));
    	}
        return "users/index";
    }

    @Override
    @PostMapping("/add")
    public String saveUser(@ModelAttribute("user") @Validated Userr user, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("Cancel")) {
            if (result.hasErrors()) {
            	model.addAttribute("user", user);
            	model.addAttribute("insts", bd.institution_findAll());
            	model.addAttribute("types", UserType.values());
                return "users/add-user";
            }
            String pwd = user.getUserPassword();
            if(!pwd.startsWith("{noop}") || pwd.equals("{noop}")) { // Si no tiene el prefijo se lo ponemos. Si la contrasenia es {noop} igual se le pone el prefijo
            	user.setUserPassword("{noop}" + pwd);
            }
            bd.user_save(user);
        }
        return "redirect:/users/";
    }
    
    @Override
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Userr user = bd.user_findById(id);
        model.addAttribute("user", user);
        model.addAttribute("insts", bd.institution_findAll());
        model.addAttribute("types", UserType.values());
        return "users/update-user";
    }
    
    @Override
    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,
                             @ModelAttribute("user") @Validated Userr user, BindingResult bindingResult, Model model) {
        if (action != null && !action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
            	model.addAttribute("insts", bd.institution_findAll());
            	model.addAttribute("types", UserType.values());
                return "users/update-user";
            }
            if(user.getUserPassword().isEmpty()){ // si la contrasenia fue dejada vacia se pone lo que hay en la base de datos
                user.setUserPassword(bd.user_findById(id).getUserPassword());
            }
            bd.user_save(user);
        }
        return "redirect:/users/";
    }
}
