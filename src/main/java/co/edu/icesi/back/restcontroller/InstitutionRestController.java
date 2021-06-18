package co.edu.icesi.back.restcontroller;

import co.edu.icesi.back.model.Institution;
import co.edu.icesi.back.repository.InstitutionRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/institutions")
public class InstitutionRestController {

    @Autowired
    private InstitutionRepositoryI institutionRepository;

    @GetMapping
    public Iterable<Institution> getInstitutions() {
        return institutionRepository.findAll();
    }

    @PostMapping
    public void addInstitution(@RequestBody Institution institution) {
        institutionRepository.save(institution);
    }

    @PutMapping
    public void updateInstitution(@RequestBody Institution institution) {
        institutionRepository.save(institution);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        institutionRepository.deleteById(id);
    }
}
