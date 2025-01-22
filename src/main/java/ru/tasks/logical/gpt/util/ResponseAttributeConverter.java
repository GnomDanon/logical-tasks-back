package ru.tasks.logical.gpt.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

public class ResponseAttributeConverter implements AttributeConverter<Response, String> {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(Response response) {
		try {
			return objectMapper.writeValueAsString(response);
		} catch (JsonProcessingException exception) {
			return null;
		}
	}

	@Override
	public Response convertToEntityAttribute(String response) {
		try {
			return objectMapper.readValue(response, Response.class);
		} catch (JsonProcessingException exception) {
			return null;
		}
	}
}
