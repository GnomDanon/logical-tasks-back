package ru.tasks.logical.task.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import ru.tasks.logical.task.entity.TestQuestions;

public class TestQuestionsAttributeConverter implements AttributeConverter<TestQuestions, String> {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(TestQuestions testQuestions) {
		try {
			return objectMapper.writeValueAsString(testQuestions);
		} catch (JsonProcessingException exception) {
			return null;
		}
	}

	@Override
	public TestQuestions convertToEntityAttribute(String value) {
		try {
			return objectMapper.readValue(value, TestQuestions.class);
		} catch (JsonProcessingException exception) {
			return null;
		}
	}
}
