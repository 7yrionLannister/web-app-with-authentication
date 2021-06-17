package co.edu.icesi.back.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.icesi.back.daos.AutotransitionDao;
import co.edu.icesi.back.model.Autotransition;


@RestController
public class AutotransitionRestController {
	@Autowired
	private AutotransitionDao autotransitionDao;
	
	@GetMapping("/api/autotransitions")
	public Iterable<Autotransition> getAutotransitions() {
		return autotransitionDao.getAll();
	}

	@PostMapping("/api/autotransitions")
	public void addAutotransition(@RequestBody Autotransition aut) {
		autotransitionDao.save(aut);
	}


}
