package co.edu.icesi.back.restcontroller;

import co.edu.icesi.back.daos.LocalconditionDao;
import co.edu.icesi.back.model.Localcondition;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class LocalconditionRestController {
    @Autowired
    LocalconditionDao localconditionDao;

    @GetMapping("/api/localconditions")
    public Iterable<Localcondition> getLocalconditions() {
        return localconditionDao.getAll();
    }

    @PostMapping("/api/localconditions")
    public void addLocalcondition(@RequestBody Localcondition loc) {
        localconditionDao.save(loc);
    }

    @PutMapping("/api/localconditions")
    public void updateLocalcondition(@RequestBody Localcondition loc) {
        localconditionDao.update(loc);
    }

    @GetMapping("/api/localconditions/{id}")
    public Localcondition getById(@PathVariable("id") Long id) {
        return localconditionDao.get(id).orElseThrow(() -> new IllegalArgumentException("Invalid id"));
    }

    @DeleteMapping("/api/localconditions/{id}")
    public void delete(@PathVariable("id") Long id) {
        localconditionDao.deleteById(id);
    }

    @GetMapping("/api/localconditions/search/findAllByName")
    public List<Localcondition> getAllByName(@RequestParam("name") String name) {
        return localconditionDao.findAllByName(name);
    }

    @GetMapping("/api/localconditions/search/findAllByPrecondition")
    public List<Localcondition> getAllByPrecondition(@RequestParam("precondition") Long precondition) {
        return localconditionDao.findAllByPrecondition(precondition);
    }

    @GetMapping("/api/localconditions/search/findAllByThreshold")
    public List<Localcondition> getAllBy(@RequestParam("threshold") Long threshold) {
        return localconditionDao.findAllByThreshold(threshold);
    }

    @GetMapping("/api/localconditions/search/findAllByType")
    public List<Localcondition> getAllByType(@RequestParam("type") String type) {
        return localconditionDao.findAllByType(type);
    }
}
