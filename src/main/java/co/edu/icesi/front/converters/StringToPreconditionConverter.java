package co.edu.icesi.front.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import co.edu.icesi.back.daos.PreconditionDao;
import co.edu.icesi.back.model.Precondition;


@WritingConverter
@Component
public class StringToPreconditionConverter implements Converter<String, Precondition> {

	@Autowired
	private PreconditionDao autDao;
	
	@Override
	public Precondition convert(String autId) {
		return autDao.get(Long.parseLong(autId)).orElse(null);
	}
	
}
