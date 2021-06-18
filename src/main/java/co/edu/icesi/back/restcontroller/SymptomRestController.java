package co.edu.icesi.back.restcontroller;

import co.edu.icesi.back.model.Symptom;
import co.edu.icesi.back.repository.SymptomRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/symptoms")
public class SymptomRestController {
    @Autowired
    private SymptomRepositoryI symptomRepository;

    @GetMapping
    public Iterable<Symptom> getSymptoms() {
        return symptomRepository.findAll();
    }

    @GetMapping("/{id}")
    public Symptom findSymptomById(@PathVariable("id") Long id){
        return symptomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid id"));
    }

    @PostMapping
    public void addSymptom(@RequestBody Symptom symptom) {
        symptomRepository.save(symptom);
    }

    @PutMapping
    public void updateSymptom(@RequestBody Symptom symptom) {
        symptomRepository.save(symptom);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        symptomRepository.deleteById(id);
    }
}
