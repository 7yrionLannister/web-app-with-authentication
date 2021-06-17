package co.edu.icesi.back.restcontroller;

import co.edu.icesi.back.daos.ThresholdDao;
import co.edu.icesi.back.model.Threshold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/thresholds")
public class ThresholdRestController {

    @Autowired
    private ThresholdDao thresholdDao;

    @GetMapping
    public List<Threshold> getThresholds() {
        return thresholdDao.getAll();
    }

    @PostMapping
    public void addThreshold(@RequestBody Threshold threshold) {
        thresholdDao.save(threshold);
    }

    @PutMapping
    public void updateThresholds(@RequestBody Threshold threshold) {
        thresholdDao.update(threshold);
    }

    @GetMapping("/{id}")
    public Threshold getbyId(@PathVariable("id") Long id) {
        return thresholdDao.get(id).orElseThrow(() -> new IllegalArgumentException("Invalid id"));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        thresholdDao.deleteById(id);
    }

    @GetMapping("/search/findAllByName")
    public List<Threshold> getAllByName(@RequestParam("name") String name) {
        return thresholdDao.findAllByName(name);
    }

    @GetMapping("/search/findAllByType")
    public List<Threshold> getAllByType(@RequestParam("type") String type) {
        return thresholdDao.findAllByType(type);
    }

    @GetMapping("/search/findAllByValue")
    public List<Threshold> getAllByValue(@RequestParam("value") String value) {
        return thresholdDao.findAllByValue(value);
    }

    @GetMapping("/search/findAllByInstitution")
    public List<Threshold> getAllByInstitution(@RequestParam("institution") Long institution) {
        return thresholdDao.findAllByInstitution(institution);
    }
}
