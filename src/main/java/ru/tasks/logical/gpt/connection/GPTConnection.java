package ru.tasks.logical.gpt.connection;

import ru.tasks.logical.common.dto.CrosswordQuestionItem;
import ru.tasks.logical.common.dto.TestItem;

public interface GPTConnection {
	String[] generateTerms(String fileName, byte[] document, int termsCount);
	CrosswordQuestionItem[] generateCrossword(String fileName, byte[] document, String[] terms, int questionsCount);
	TestItem[] generateTest(String fileName, byte[] document, String[] terms, int questionsCount);
}
