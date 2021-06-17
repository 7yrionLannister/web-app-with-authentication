package co.edu.icesi.front.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import co.edu.icesi.back.daos.AutotransitionDao;
import co.edu.icesi.back.model.Autotransition;


@WritingConverter
@Component
public class StringToAutotransitionConverter implements Converter<String, Autotransition> {

	@Autowired
	private AutotransitionDao autDao;
	
	@Override
	public Autotransition convert(String autId) {
		return autDao.get(Long.parseLong(autId)).orElse(null);
	}
	
}
