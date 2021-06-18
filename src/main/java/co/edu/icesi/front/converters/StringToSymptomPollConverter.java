package co.edu.icesi.front.converters;

import co.edu.icesi.front.businessdelegate.BusinessDelgateI;
import co.edu.icesi.front.model.Symptompoll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@WritingConverter
@Component
public class StringToSymptomPollConverter implements Converter<String, Symptompoll> {
    @Autowired
    private BusinessDelgateI bd;

    @Override
    public Symptompoll convert(String source) {
        return bd.findSymptompollById(Long.parseLong(source));
    }
}
