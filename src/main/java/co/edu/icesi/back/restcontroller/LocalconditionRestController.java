package co.edu.icesi.back.restcontroller;

import co.edu.icesi.back.daos.LocalconditionDao;
import co.edu.icesi.back.model.Localcondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/localconditions")
public class LocalconditionRestController {

    @Autowired
    LocalconditionDao localconditionDao;

    @GetMapping
    public Iterable<Localcondition> getLocalconditions() {
        return localconditionDao.getAll();
    }

    @PostMapping
    public void addLocalcondition(@RequestBody Localcondition loc) {
        localconditionDao.save(loc);
    }

    @PutMapping
    public void updateLocalcondition(@RequestBody Localcondition loc) {
        localconditionDao.update(loc);
    }

    @GetMapping("/{id}")
    public Localcondition getById(@PathVariable("id") Long id) {
        return localconditionDao.get(id).orElseThrow(() -> new IllegalArgumentException("Invalid id"));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        localconditionDao.deleteById(id);
    }

    @GetMapping("/search/findAllByName")
    public List<Localcondition> getAllByName(@RequestParam("name") String name) {
        return localconditionDao.findAllByName(name);
    }

    @GetMapping("/search/findAllByPrecondition")
    public List<Localcondition> getAllByPrecondition(@RequestParam("precondition") Long precondition) {
        return localconditionDao.findAllByPrecondition(precondition);
    }

    @GetMapping("/search/findAllByThreshold")
    public List<Localcondition> getAllBy(@RequestParam("threshold") Long threshold) {
        return localconditionDao.findAllByThreshold(threshold);
    }

    @GetMapping("/search/findAllByType")
    public List<Localcondition> getAllByType(@RequestParam("type") String type) {
        return localconditionDao.findAllByType(type);
    }
}
