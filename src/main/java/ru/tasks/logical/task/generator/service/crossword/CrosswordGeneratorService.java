package ru.tasks.logical.task.generator.service.crossword;

import ru.tasks.logical.crossword.CrosswordPuzzleGenerator;
import ru.tasks.logical.task.entity.Crossword;
import ru.tasks.logical.task.entity.CrosswordQuestions;

public class CrosswordGeneratorService implements CrosswordGenerator {
	@Override
	public Crossword generateCrossword(CrosswordQuestions data) {
		CrosswordPuzzleGenerator generator = new CrosswordPuzzleGenerator(data.getQuestions());
		return generator.createCrosswordPuzzle();
	}
}
