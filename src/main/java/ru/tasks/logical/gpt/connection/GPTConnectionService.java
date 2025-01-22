package ru.tasks.logical.gpt.connection;

import lombok.extern.java.Log;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.tasks.logical.common.dto.CrosswordQuestionItem;
import ru.tasks.logical.common.dto.TestItem;
import ru.tasks.logical.gpt.util.Response;
import ru.tasks.logical.gpt.util.ResponseAttributeConverter;
import ru.tasks.logical.task.converter.CrosswordQuestionsAttributeConverter;
import ru.tasks.logical.task.converter.TestQuestionsAttributeConverter;

@Log
public class GPTConnectionService implements GPTConnection{

	private final RestTemplate restTemplate = new RestTemplate();
	private final CrosswordQuestionsAttributeConverter crosswordConverter = new CrosswordQuestionsAttributeConverter();
	private final TestQuestionsAttributeConverter testConverter = new TestQuestionsAttributeConverter();
	private final ResponseAttributeConverter responseConverter = new ResponseAttributeConverter();

	private String sendRequest(String fileName, byte[] fileBytes, int questionsCount, String path) {
		String url = "http://localhost:5000/" + path;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		ByteArrayResource fileResource = new ByteArrayResource(fileBytes) {
			@NonNull
			@Override
			public String getFilename() {
				return fileName;
			}
		};

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", fileResource);
		body.add("questions_count", questionsCount);

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				url,
				HttpMethod.POST,
				requestEntity,
				String.class
		);

		Response customResponse = responseConverter.convertToEntityAttribute(response.getBody());
		return customResponse.getResult();
	}

	@Override
	public String[] generateTerms(String fileName, byte[] document, int termsCount) {
		return new String[0];
	}

	@Override
	public CrosswordQuestionItem[] generateCrossword(String fileName, byte[] document, String[] terms, int questionsCount) {
		String result = sendRequest(fileName, document, questionsCount, "crossword");
		return crosswordConverter.convertToEntityAttribute("{\"questions\":" + result + "}").getQuestions();
	}

	@Override
	public TestItem[] generateTest(String fileName, byte[] document, String[] terms, int questionsCount) {
		String result = sendRequest(fileName, document, questionsCount, "test");
		return testConverter.convertToEntityAttribute("{\"questions\":" + result + "}").getQuestions();
	}
}
