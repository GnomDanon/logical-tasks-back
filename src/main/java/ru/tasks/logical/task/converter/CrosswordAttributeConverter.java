package ru.tasks.logical.task.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import ru.tasks.logical.task.entity.Crossword;

public class CrosswordAttributeConverter implements AttributeConverter<Crossword, String> {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(Crossword crosswordItem) {
		try {
			return objectMapper.writeValueAsString(crosswordItem);
		} catch (JsonProcessingException exception) {
			return null;
		}
	}

	@Override
	public Crossword convertToEntityAttribute(String value) {
		try {
			return objectMapper.readValue(value, Crossword.class);
		} catch (JsonProcessingException exception) {
			return null;
		}
	}
}
