package co.edu.icesi.back.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.back.model.Institution;
import co.edu.icesi.back.repository.InstitutionRepositoryI;

@Service
public class InstitutionService implements InstitutionServiceI {

	private InstitutionRepositoryI institutionRepository;
	
	public InstitutionService(InstitutionRepositoryI institutionRepository) {
		this.institutionRepository = institutionRepository;
	}
	
	@Override
	public <S extends Institution> S save(S instotransition) {
		institutionRepository.save(instotransition);
		return instotransition;
	}
	
	@Override
	public <S extends Institution> Iterable<S> saveAll(Iterable<S> insts) {
		for(Institution inst : insts) {
			save(inst);
		}
		return insts;
	}

	@Override
	public Optional<Institution> findById(Long id) {
		return institutionRepository.findById(id);
	}
	
	@Override
	public boolean existsById(Long id) {
		return institutionRepository.existsById(id);
	}

	@Override
	public Iterable<Institution> findAll() {
		return institutionRepository.findAll();
	}

	@Override
	public Iterable<Institution> findAllById(Iterable<Long> ids) {
		return institutionRepository.findAllById(ids);
	}

	@Override
	public long count() {
		return institutionRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		institutionRepository.deleteById(id);
	}

	@Override
	public void delete(Institution instotransition) {
		institutionRepository.delete(instotransition);
	}

	@Override
	public void deleteAll(Iterable<? extends Institution> insts) {
		institutionRepository.deleteAll(insts);
	}

	@Override
	public void deleteAll() {
		institutionRepository.deleteAll();
	}

	@Override
	public void editInstitution(long id, String academicServerUrl, String acadextradataurl, String acadloginpassword, String acadloginurl
			, String acadloginusername, String acadpersoninfodocurl, String acadpersoninfoidurl, String acadphysicalspacesurl, String acadprogrammedcoursesurl
			, String ldapbasedn, String ldappassword, String ldapurl, String ldapusername, String ldapusersearchbase, String name) {
		Institution inst = findById(id).get();
		inst.setInstAcademicserverurl(academicServerUrl);
		inst.setInstAcadextradataurl(acadextradataurl);
		inst.setInstAcadloginpassword(acadloginpassword);
		inst.setInstAcadloginurl(acadloginurl);
		inst.setInstAcadloginusername(acadloginusername);
		inst.setInstAcadpersoninfodocurl(acadpersoninfodocurl);
		inst.setInstAcadpersoninfoidurl(acadpersoninfoidurl);
		inst.setInstAcadphysicalspacesurl(acadphysicalspacesurl);
		inst.setInstAcadprogrammedcoursesurl(acadprogrammedcoursesurl);
		inst.setInstLdapbasedn(ldapbasedn);
		inst.setInstLdappassword(ldappassword);
		inst.setInstLdapurl(ldapurl);
		inst.setInstLdapusername(ldapusername);
		inst.setInstLdapusersearchbase(ldapusersearchbase);
		inst.setInstName(name);
		institutionRepository.save(inst);
	}
}
