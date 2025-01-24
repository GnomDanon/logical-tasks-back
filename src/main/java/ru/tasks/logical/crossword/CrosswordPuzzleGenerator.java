package ru.tasks.logical.crossword;

import ru.tasks.logical.common.dto.CrosswordQuestionItem;
import ru.tasks.logical.task.entity.Crossword;
import ru.tasks.logical.task.entity.CrosswordItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class CrosswordPuzzleGenerator {
	private final Random random = new Random();

	private final int attemptsToFitWords = 100;
	private final int gridsToMake = 20;
	private int gridSize;

	private final List<String> usedWords = new ArrayList<>();
	private final List<CrosswordPuzzle> generatedGrids = new ArrayList<>();
	private final Set<Character> goodStartedLetters = new HashSet<>();

	private final List<String> words;
	private final Map<String, String> questionsMemory = new HashMap<>();

	public CrosswordPuzzleGenerator(CrosswordQuestionItem[] words) {
		this.words = Arrays.stream(words).map(item -> item.getAnswer().toLowerCase()).toList();
		gridSize = 0;
		for (CrosswordQuestionItem item : words) {
			questionsMemory.put(item.getAnswer().toLowerCase(), item.getQuestion());
			gridSize += item.getAnswer().length();
		}
//		gridSize /= 2;
	}

	public Crossword createCrosswordPuzzle() {
		generateGrids();
		CrosswordPuzzle bestGrid = getBestGrid(generatedGrids);
		List<Word> bestWords = bestGrid.getWords();

		int verticalCount = bestGrid.getVerticalCount();
		int horizontalCount = bestGrid.getHorizontalCount();

		CrosswordItem[] vertical = new CrosswordItem[verticalCount];
		CrosswordItem[] horizontal = new CrosswordItem[horizontalCount];

		int verticalIndex = 0;
		int horizontalIndex = 0;
		for (Word word : bestWords) {
			String question = questionsMemory.get(word.getText());
			if (word.isVertical()) {
				vertical[verticalIndex] = new CrosswordItem(question, word.getText(), word.getRow(), word.getColumn());
				verticalIndex++;
			} else {
				horizontal[horizontalIndex] = new CrosswordItem(question, word.getText(), word.getRow(), word.getColumn());
				horizontalIndex++;
			}
		}
		return new Crossword(horizontal, vertical);
	}

	private boolean attemptToPlaceWordOnGrid(CrosswordPuzzle grid, Word word) {
		String text = getAWordToTry();
		for (int row = 0; row < gridSize; row++) {
			for (int column = 0; column < gridSize; column++) {
				word.setText(text);
				word.setRow(row);
				word.setColumn(column);
				word.setVertical(random.nextBoolean());

				if (grid.isLetter(row, column)) {
					if (grid.update(word)) {
						pushUserWords(word.getText());
						return true;
					}
				}
			}
		}
		return false;
	}

	private String getAWordToTry() {
		String word = getRandomWord();
		boolean goodWord = isGoodWord(word);

//		while (usedWords.contains(word) || !goodWord) {
//			word = getRandomWord();
//			goodWord = isGoodWord(word);
//		}
		return word;
	}

	private boolean isGoodWord(String word) {
		boolean goodWord = false;
		for (char letter : goodStartedLetters) {
			if (letter == word.charAt(0)) {
				goodWord = true;
				break;
			}
		}
		return goodWord;
	}

	private CrosswordPuzzle getBestGrid(List<CrosswordPuzzle> grids) {
		CrosswordPuzzle bestGrid = grids.get(0);
		for (CrosswordPuzzle grid : grids) {
			if (grid.getWords().size() >= bestGrid.getWords().size()) {
				bestGrid = grid;
			}
		}
		return bestGrid;
	}

	private void generateGrids() {
		for (int gridsMade = 0; gridsMade < gridsToMake; gridsMade++) {
			for (int row = 0; row < gridSize; row++) {
				for (int column = 0; column < gridSize; column++) {
					CrosswordPuzzle grid = new CrosswordPuzzle(gridSize);
					Word word = new Word(getRandomWord(), row, column, false);

					grid.update(word);
					pushUserWords(word.getText());

					int continuousFails = 0;
					for (int attempts = 0; attempts < attemptsToFitWords && !getUnusedWords().isEmpty(); attempts++) {
						boolean placed = attemptToPlaceWordOnGrid(grid, word);
						if (placed) {
							continuousFails = 0;
						} else {
							continuousFails++;
						}
						if (continuousFails > 470) {
							break;
						}
					}
					generatedGrids.add(grid);
					usedWords.clear();
				}
			}
		}
	}

	private void pushUserWords(String text) {
		usedWords.add(text);
		char[] characters = text.toCharArray();
		for (char letter : characters) {
			goodStartedLetters.add(letter);
		}
	}

	private List<String> getUnusedWords() {
		return words.stream().filter(word -> !usedWords.contains(word)).toList();
	}

	private String getWithMaxSize(List<String> wordList) {
		return wordList.stream().max(Comparator.comparing(String::length)).orElse("");
	}

	private String getRandomWord() {
		List<String> unusedWords = getUnusedWords();
		return unusedWords.get(getRandomInt(unusedWords.size()));
	}

	private int getRandomInt(int max) {
		return random.nextInt(max);
	}
}
