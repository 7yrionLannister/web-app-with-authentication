package co.edu.icesi.front.converters;

import co.edu.icesi.front.businessdelegate.BusinessDelgateI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import co.edu.icesi.front.model.Autotransition;


@WritingConverter
@Component
public class StringToAutotransitionConverter implements Converter<String, Autotransition> {

	@Autowired
	private BusinessDelgateI bd;
	
	@Override
	public Autotransition convert(String autId) {
		return bd.findAutotransitionById(Long.parseLong(autId));
	}
	
}
