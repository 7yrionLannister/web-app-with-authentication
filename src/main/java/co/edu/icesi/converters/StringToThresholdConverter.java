package co.edu.icesi.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import co.edu.icesi.daos.ThresholdDao;
import co.edu.icesi.model.Threshold;


@WritingConverter
@Component
public class StringToThresholdConverter implements Converter<String, Threshold> {

	@Autowired
	private ThresholdDao autDao;
	
	@Override
	public Threshold convert(String autId) {
		return autDao.get(Long.parseLong(autId)).orElse(null);
	}
	
}
