package co.edu.icesi.back.restcontroller;

import co.edu.icesi.back.daos.PreconditionDao;
import co.edu.icesi.back.model.Precondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/preconditions")
public class PreconditionRestController {

    @Autowired
    PreconditionDao preconditionDao;

    @GetMapping
    public List<Precondition> getPreconditions() {
        return preconditionDao.getAll();
    }

    @PostMapping
    public void addPrecondition(@RequestBody Precondition pre) {
        preconditionDao.save(pre);
    }

    @PutMapping
    public void updatePrecondition(@RequestBody Precondition pre) {
        preconditionDao.update(pre);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        preconditionDao.deleteById(id);
    }

    @GetMapping("/search/findAllByAutotransition")
    public List<Precondition> getAllByAutotransition(@RequestParam("autotransition") Long autotransition) {
        return preconditionDao.findAllByAutotransition(autotransition);
    }

    @GetMapping("/search/findAllWithAtLeastTwoLocalconditionsWithAThresholdWithValueGreatherThanOne")
    public List<Precondition> getAllWithAtLeastTwoLocalconditionsWithAThresholdWithValueGreatherThanOne() {
        return preconditionDao.findAllWithAtLeastTwoLocalconditionsWithAThresholdWithValueGreatherThanOne();
    }
}
