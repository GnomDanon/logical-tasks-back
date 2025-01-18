package ru.tasks.logical.gpt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tasks.logical.common.dto.CrosswordQuestionItem;
import ru.tasks.logical.common.dto.TestItem;
import ru.tasks.logical.gpt.connection.GPTConnection;

@Service
@RequiredArgsConstructor
public class GPTService {

	private final GPTConnection gptConnection;

	public String[] generateTerms(byte[] document, int termsCount) {
		return gptConnection.generateTerms(document, termsCount);
	}

	public CrosswordQuestionItem[] generateCrossword(byte[] document, String[] terms, int questionsCount) {
		return gptConnection.generateCrossword(document, terms, questionsCount);
	}

	public TestItem[] generateTest(byte[] document, String[] terms, int questionsCount) {
		return gptConnection.generateTest(document, terms, questionsCount);
	}
}
