package co.edu.icesi.back.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.icesi.back.daos.AutotransitionDao;
import co.edu.icesi.back.model.Autotransition;

import java.util.List;


@RestController
@RequestMapping("/api/autotransitions")
public class AutotransitionRestController {

	@Autowired
	private AutotransitionDao autotransitionDao;
	
	@GetMapping
	public Iterable<Autotransition> getAutotransitions() {
		return autotransitionDao.getAll();
	}

	@PostMapping
	public void addAutotransition(@RequestBody Autotransition aut) {
		autotransitionDao.save(aut);
	}

	@PutMapping
	public void updateAutotransition(@RequestBody Autotransition aut) {
		autotransitionDao.update(aut);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		autotransitionDao.deleteById(id);
	}

	@GetMapping("/{id}")
	public Autotransition getById(@PathVariable("id") Long id) {
		return autotransitionDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id"));
	}

	@GetMapping("/search/findAllByActive")
	public List<Autotransition> getAllByActive(@RequestParam("active") String active) {
		return autotransitionDao.findAllByActive(active);
	}

	@GetMapping("/search/findAllByName")
	public List<Autotransition> getAllByName(@RequestParam("name") String name) {
		return autotransitionDao.findAllByName(name);
	}

	@GetMapping("/search/findAllByInstitutionInstId")
	public List<Autotransition> getAllByInstitutionInstId(@RequestParam("institution") Long institution) {
		return autotransitionDao.findAllByInstitutionInstId(institution);
	}

	@GetMapping("/search/findAllByLogicalOperand")
	public List<Autotransition> getAllByLogicalOperand(@RequestParam("operand") String operand) {
		return autotransitionDao.findAllByLogicalOperand(operand);
	}
}
