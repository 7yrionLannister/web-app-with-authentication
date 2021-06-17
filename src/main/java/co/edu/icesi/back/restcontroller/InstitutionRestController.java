package co.edu.icesi.back.restcontroller;

import co.edu.icesi.back.model.Institution;
import co.edu.icesi.back.model.Localcondition;
import co.edu.icesi.back.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/institutions")
public class InstitutionRestController {

    @Autowired
    private InstitutionService instService;

    @GetMapping
    public Iterable<Institution> getInstitutions() {
        return instService.findAll();
    }

}
