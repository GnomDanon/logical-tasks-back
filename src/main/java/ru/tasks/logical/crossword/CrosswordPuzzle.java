package ru.tasks.logical.crossword;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CrosswordPuzzle {
	private final char emptyCell = '_';
	private final int gridSize;
	private final char[][] grid;

	@Getter
	private final List<Word> words = new ArrayList<>();

	@Getter
	private int verticalCount = 0;

	@Getter
	private int horizontalCount = 0;

	public CrosswordPuzzle(int gridSize) {
		this.gridSize = gridSize;
		this.grid = new char[gridSize][gridSize];
		for (int row = 0; row < gridSize; row++) {
			for (int column = 0; column < gridSize; column++) {
				grid[row][column] = emptyCell;
			}
		}
	}

	public boolean update(Word word) {
		boolean updated = false;
		if (canBePlaced(word)) {
			addWord(word);
			updated = true;
		}
		return updated;
	}

	private boolean canBePlaced(Word word) {
		boolean canBePlaced = true;
		if (isValidPosition(word.getRow(), word.getColumn()) && fitsOnGrid(word)) {
			int index = 0;
			while (index < word.getText().length()) {
				int currentRow = word.isVertical() ? word.getRow() + index : word.getRow();
				int currentColumn = !word.isVertical() ? word.getColumn() + index : word.getColumn();

				if ((word.getText().charAt(index) == grid[currentRow][currentColumn] ||
						emptyCell == grid[currentRow][currentColumn]) &&
					placementLegal(word, currentRow, currentColumn)) {

					canBePlaced = true;
				} else {
					canBePlaced = false;
					break;
				}
				index++;
			}
		} else {
			canBePlaced = false;
		}
		return canBePlaced;
	}

	public int getIntersections() {
		int intersections = 0;
		for (int row = 0; row < gridSize; row++) {
			for (int column = 0; column < gridSize; column++) {
				if (isLetter(row, column)) {
					if (isValidPosition(row - 1, column) &&
					isValidPosition(row + 1, column) &&
					isValidPosition(row, column - 1) &&
					isValidPosition(row, column + 1) &&
					isLetter(row - 1, column) &&
					isLetter(row + 1, column) &&
					isLetter(row, column - 1) &&
					isLetter(row, column + 1)) {

						intersections++;
					}
				}
			}
		}
		return intersections;
	}

	private boolean placementLegal(Word word, int row, int column) {
		boolean illegal = false;
		if (word.isVertical()) {
			illegal = isInterference(row, column + 1, row + 1, column) ||
					isInterference(row, column - 1, row + 1, column) ||
					overwritingVerticalWord(row, column) ||
					invadingTerritory(word, row, column);
		} else {
			illegal = isInterference(row + 1, column, row, column + 1) ||
					isInterference(row - 1, column, row, column + 1) ||
					overwritingHorizontalWord(row, column) ||
					invadingTerritory(word, row, column);
		}
		return !illegal;
	}

	private boolean invadingTerritory(Word word, int row, int column) {
		boolean invading = false;
		boolean empty = isEmptyCell(row, column);
		if (word.isVertical()) {
			boolean weHaveNeighbours = (doesCharacterExists(row, column - 1) ||
					doesCharacterExists(row, column + 1)) ||
					endOfWord(word, row, column) &&
							doesCharacterExists(row + 1, column);
			invading = empty && weHaveNeighbours;
		} else {
			boolean weHaveNeighbours = (doesCharacterExists(row - 1, column) ||
					doesCharacterExists(row + 1, column)) ||
					endOfWord(word, row, column) &&
							doesCharacterExists(row, column + 1);
			invading = empty && weHaveNeighbours;
		}
		return invading;
	}

	private boolean endOfWord(Word word, int row, int column) {
		if (word.isVertical()) {
			return word.getRow() + word.getText().length() - 1 == row;
		} else {
			return word.getColumn() + word.getText().length() - 1 == column;
		}
	}

	private boolean doesCharacterExists(int row, int column) {
		return isValidPosition(row, column) &&
				isLetter(row, column);
	}

	private boolean overwritingHorizontalWord(int row, int column) {
		int leftColumn = column - 1;
		return isValidPosition(row, leftColumn) &&
				isLetter(row, column) &&
				isLetter(row, leftColumn);
	}

	private boolean overwritingVerticalWord(int row, int column) {
		int rowAbove = row - 1;
		return isValidPosition(rowAbove, column) &&
				isLetter(row, column) &&
				isLetter(rowAbove, column);
	}

	private boolean isInterference(int row, int column, int nextRow, int nextColumn) {
		return isValidPosition(row, column) &&
				isValidPosition(nextRow, nextColumn) &&
				isLetter(row, column) &&
				isLetter(nextRow, nextColumn);
	}

	public boolean isLetter(int row, int column) {
		return grid[row][column] != emptyCell;
	}

	private boolean isEmptyCell(int row, int column) {
		return !isLetter(row, column);
	}

	private void addWord(Word word) {
		for (int letterIndex = 0; letterIndex < word.getText().length(); letterIndex++) {
			int row = word.getRow();
			int column = word.getColumn();
			if (word.isVertical()) {
				row += letterIndex;
			} else {
				column += letterIndex;
			}
			grid[row][column] = word.getText().charAt(letterIndex);
		}
		words.add(word.copy());
		if (word.isVertical()) {
			verticalCount++;
		} else {
			horizontalCount++;
		}
	}

	private boolean fitsOnGrid(Word word) {
		if (word.isVertical()) {
			return word.getRow() + word.getText().length() <= gridSize;
		} else {
			return word.getColumn() + word.getText().length() <= gridSize;
		}
	}

	private boolean isValidPosition(int row, int column) {
		return row >= 0 && row < gridSize && column >= 0 && column < gridSize;
	}
}
