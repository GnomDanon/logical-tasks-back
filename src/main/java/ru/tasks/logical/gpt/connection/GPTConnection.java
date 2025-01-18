package ru.tasks.logical.gpt.connection;

import ru.tasks.logical.common.dto.CrosswordQuestionItem;
import ru.tasks.logical.common.dto.TestItem;

public interface GPTConnection {
	String[] generateTerms(byte[] document, int termsCount);
	CrosswordQuestionItem[] generateCrossword(byte[] document, String[] terms, int questionsCount);
	TestItem[] generateTest(byte[] document, String[] terms, int questionsCount);
}
