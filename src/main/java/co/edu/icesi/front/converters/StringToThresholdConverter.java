package co.edu.icesi.front.converters;

import co.edu.icesi.front.businessdelegate.BusinessDelgateI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import co.edu.icesi.front.model.Threshold;


@WritingConverter
@Component
public class StringToThresholdConverter implements Converter<String, Threshold> {

	@Autowired
	private BusinessDelgateI bd;
	
	@Override
	public Threshold convert(String autId) {
		return bd.threshold_findById(Long.parseLong(autId));
	}
	
}
