package co.edu.icesi.front.converters;

import co.edu.icesi.front.businessdelegate.BusinessDelgateI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import co.edu.icesi.front.model.Precondition;


@WritingConverter
@Component
public class StringToPreconditionConverter implements Converter<String, Precondition> {

	@Autowired
	private BusinessDelgateI bd;
	
	@Override
	public Precondition convert(String autId) {
		return bd.precondition_get(Long.parseLong(autId));
	}
	
}
