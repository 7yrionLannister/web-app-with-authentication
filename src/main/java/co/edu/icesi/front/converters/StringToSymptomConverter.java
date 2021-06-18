package co.edu.icesi.front.converters;

import co.edu.icesi.front.businessdelegate.BusinessDelgateI;
import co.edu.icesi.front.model.Symptom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@WritingConverter
@Component
public class StringToSymptomConverter implements Converter<String, Symptom> {
    @Autowired
    private BusinessDelgateI bd;

    @Override
    public Symptom convert(String source) {
        return bd.findSymptomById(Long.parseLong(source));
    }
}
