package ru.tasks.logical.task.generator.service.crossword;

import ru.tasks.logical.task.entity.Crossword;
import ru.tasks.logical.task.entity.CrosswordItem;
import ru.tasks.logical.task.entity.CrosswordQuestions;

public class MockCrosswordGenerator implements CrosswordGenerator {

	Crossword crossword = new Crossword(
			new CrosswordItem[]{
					new CrosswordItem("Кто дуреет с прикормки?", "Карась", 1, 0),
					new CrosswordItem("Что кричит медведь?", "Гол", 4, 1),
					new CrosswordItem("Кто кричит гол?", "Медведь", 6, 2),
			},
			new CrosswordItem[]{
					new CrosswordItem("С чего дуреет карась?", "Прикормка", 0, 2)
			}
	);

	@Override
	public Crossword generateCrossword(CrosswordQuestions data) {
		return crossword;
	}
}
