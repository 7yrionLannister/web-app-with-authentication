package co.edu.icesi.front.converters;

import co.edu.icesi.front.businessdelegate.BusinessDelgateI;
import co.edu.icesi.front.model.Institution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@WritingConverter
@Component
public class StringToInstitutionConverter implements Converter<String, Institution> {

    @Autowired
    private BusinessDelgateI bd;

    @Override
    public Institution convert(String autId) {
        return bd.findInstitutionById(Long.parseLong(autId));
    }

}
