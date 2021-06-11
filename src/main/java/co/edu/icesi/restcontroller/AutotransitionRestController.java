package co.edu.icesi.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.daos.AutotransitionDao;
import co.edu.icesi.model.Autotransition;


@RestController
public class AutotransitionRestController {
	@Autowired
	private AutotransitionDao autotransitionDao;
	
	@RequestMapping("api/autotransitions")
	public Iterable<Autotransition> getAutotransitions() {
		System.out.println("REEEEEEEEEEEEEEEEEEEEEEEESSSSSSSSSSST");
		return autotransitionDao.getAll();
	}
}
