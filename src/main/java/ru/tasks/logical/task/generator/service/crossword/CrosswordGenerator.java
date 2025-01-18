package ru.tasks.logical.task.generator.service.crossword;

import ru.tasks.logical.task.entity.Crossword;
import ru.tasks.logical.task.entity.CrosswordQuestions;

public interface CrosswordGenerator {
	Crossword generateCrossword(CrosswordQuestions data);
}
