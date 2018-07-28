package com.vetweb.dao.converter;

import java.time.Period;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class FrequenciaConverter implements AttributeConverter<Period, String>{

	@Override
	public String convertToDatabaseColumn(Period period) {
		return period.toString();
	}

	@Override
	public Period convertToEntityAttribute(String dbData) {
		return Period.parse(dbData);
	}

}
