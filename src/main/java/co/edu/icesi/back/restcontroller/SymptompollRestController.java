package co.edu.icesi.back.restcontroller;

import co.edu.icesi.back.model.Symptompoll;
import co.edu.icesi.back.repository.SymptompollRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/symptompolls")
public class SymptompollRestController {
    @Autowired
    private SymptompollRepositoryI symptompollRepository;

    @GetMapping
    public Iterable<Symptompoll> getSymptompolls() {
        return symptompollRepository.findAll();
    }

    @GetMapping("/{id}")
    public Symptompoll findSymptompollById(@PathVariable("id") Long id){
        return symptompollRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid id"));
    }

    @PostMapping
    public void addSymptompoll(@RequestBody Symptompoll symptompoll) {
        symptompollRepository.save(symptompoll);
    }

    @PutMapping
    public void updateSymptompoll(@RequestBody Symptompoll symptompoll) {
        symptompollRepository.save(symptompoll);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        symptompollRepository.deleteById(id);
    }
}
