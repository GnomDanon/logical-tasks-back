package ru.tasks.logical.task.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.tasks.logical.task.entity.CrosswordQuestions;

@Converter
public class CrosswordQuestionsAttributeConverter implements AttributeConverter<CrosswordQuestions, String> {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(CrosswordQuestions crosswordQuestions) {
		try {
			return objectMapper.writeValueAsString(crosswordQuestions);
		} catch (JsonProcessingException exception) {
			return null;
		}
	}

	@Override
	public CrosswordQuestions convertToEntityAttribute(String value) {
		try {
			return objectMapper.readValue(value, CrosswordQuestions.class);
		} catch (JsonProcessingException exception) {
			return null;
		}
	}
}
