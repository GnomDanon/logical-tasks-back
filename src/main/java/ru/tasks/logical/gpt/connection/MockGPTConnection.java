package ru.tasks.logical.gpt.connection;

import ru.tasks.logical.common.dto.CrosswordQuestionItem;
import ru.tasks.logical.common.dto.TestItem;

public class MockGPTConnection implements GPTConnection {
	String[] terms = new String[] {"Карась", "Прикормка", "Гол", "Медведь"};
	CrosswordQuestionItem[] crosswordQuestionItems = new CrosswordQuestionItem[] {
			new CrosswordQuestionItem("Кто дуреет с этой прикормки?", "Карась"),
			new CrosswordQuestionItem("С чего дуреет карась?", "Прикормка"),
			new CrosswordQuestionItem("Кто кричит 'Гол'?", "Медведь"),
			new CrosswordQuestionItem("Что кричит медведь?", "Гол")
	};
	TestItem[] testItems = new TestItem[] {
			new TestItem(
					"Кто дуреет с этой прикормки?",
					new String[] {"Медведь", "Карась"},
					0
			),
			new TestItem(
					"С чего дуреет карась?",
					new String[] {"Кондиции", "Прикормка"},
					1
			),
			new TestItem(
					"Кто кричит 'Гол'?",
					new String[] {"Волк", "Заяц", "Медведь", "Мышь"},
					2
			),
			new TestItem(
					"Что кричит медведь?",
					new String[] {"Кидай", "Бой", "Бей", "Гол"},
					3
			)
	};

	@Override
	public String[] generateTerms(byte[] document, int termsCount) {
		return terms;
	}

	@Override
	public CrosswordQuestionItem[] generateCrossword(byte[] document, String[] terms, int questionsCount) {
		return crosswordQuestionItems;
	}

	@Override
	public TestItem[] generateTest(byte[] document, String[] terms, int questionsCount) {
		return testItems;
	}
}
