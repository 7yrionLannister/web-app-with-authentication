package co.edu.icesi.back.restcontroller;

import co.edu.icesi.back.model.Symptomquestion;
import co.edu.icesi.back.repository.SymptomquestionRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/symptomquestions")
public class SymptomquestionRestController {
    @Autowired
    private SymptomquestionRepositoryI symptomquestionRepository;

    @GetMapping
    public Iterable<Symptomquestion> getSymptomquestions() {
        return symptomquestionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Symptomquestion findSymptomquestionById(@PathVariable("id") Long id) {
        return symptomquestionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid id"));
    }

    @PostMapping
    public void addSymptomquestion(@RequestBody Symptomquestion Symptom) {
        symptomquestionRepository.save(Symptom);
    }

    @PutMapping
    public void updateSymptomquestion(@RequestBody Symptomquestion Symptom) {
        symptomquestionRepository.save(Symptom);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        symptomquestionRepository.deleteById(id);
    }
}
