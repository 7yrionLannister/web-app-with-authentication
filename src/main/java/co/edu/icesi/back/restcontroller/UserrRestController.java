package co.edu.icesi.back.restcontroller;

import co.edu.icesi.back.model.Institution;
import co.edu.icesi.back.model.Userr;
import co.edu.icesi.back.repository.InstitutionRepositoryI;
import co.edu.icesi.back.repository.UserRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userrs")
public class UserrRestController {
    @Autowired
    private UserRepositoryI userRepository;
    @Autowired
    private InstitutionRepositoryI institutionRepository;

    @GetMapping
    public Iterable<Userr> getUserrs() {
        return userRepository.findAll();
    }

    @PostMapping
    public void addUserr(@RequestBody Userr userr) {
        userRepository.save(userr);
    }

    @PutMapping
    public void updateUserr(@RequestBody Userr userr) {
        userRepository.save(userr);
    }

    @GetMapping("/search/findAllByInstitution")
    public List<Userr> getByInstitutionInstId(@RequestParam("institution") Long inst) {
        return userRepository.findAllByInstitution(institutionRepository.findById(inst).orElseThrow(() -> new IllegalArgumentException("Invalid id")));
    }

    @GetMapping("/search/findByUserName")
    public Userr getByUsername(@RequestParam("name") String name) {
        return userRepository.findByUserName(name);
    }
}
