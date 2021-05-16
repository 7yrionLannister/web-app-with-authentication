package co.edu.icesi.service;

import java.util.Optional;

import co.edu.icesi.model.Institution;

public interface InstitutionServiceI {

	<S extends Institution> S save(S instotransition);

	<S extends Institution> Iterable<S> saveAll(Iterable<S> insts);

	Optional<Institution> findById(Long id);

	boolean existsById(Long id);

	Iterable<Institution> findAllById(Iterable<Long> ids);

	Iterable<Institution> findAll();

	long count();

	void deleteById(Long id);

	void delete(Institution instotransition);

	void deleteAll(Iterable<? extends Institution> insts);

	void deleteAll();

	void editInstitution(long id, String academicServerUrl, String acadextradataurl, String acadloginpassword,
			String acadloginurl, String acadloginusername, String acadpersoninfodocurl, String acadpersoninfoidurl,
			String acadphysicalspacesurl, String acadprogrammedcoursesurl, String ldapbasedn, String ldappassword,
			String ldapurl, String ldapusername, String ldapusersearchbase, String name);

}
