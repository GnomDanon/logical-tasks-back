package ru.tasks.logical.task.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import ru.tasks.logical.task.entity.CrosswordItem;

public class CrosswordItemAttributeConverter implements AttributeConverter<CrosswordItem, String> {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(CrosswordItem crosswordItem) {
		try {
			return objectMapper.writeValueAsString(crosswordItem);
		} catch (JsonProcessingException exception) {
			return null;
		}
	}

	@Override
	public CrosswordItem convertToEntityAttribute(String value) {
		try {
			return objectMapper.readValue(value, CrosswordItem.class);
		} catch (JsonProcessingException exception) {
			return null;
		}
	}
}
