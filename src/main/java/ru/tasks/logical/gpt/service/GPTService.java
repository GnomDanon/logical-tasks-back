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

	public String[] generateTerms(String fileName, byte[] document, int termsCount) {
		return gptConnection.generateTerms(fileName, document, termsCount);
	}

	public CrosswordQuestionItem[] generateCrossword(String fileName, byte[] document, String[] terms, int questionsCount) {
		return gptConnection.generateCrossword(fileName, document, terms, questionsCount);
	}

	public TestItem[] generateTest(String fileName, byte[] document, String[] terms, int questionsCount) {
		return gptConnection.generateTest(fileName, document, terms, questionsCount);
	}
}
