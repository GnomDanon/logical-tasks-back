package ru.tasks.logical.crossword;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Word {
	private String text;
	private int row;
	private int column;
	private boolean vertical;

	public Word copy() {
		return new Word(text, row, column, vertical);
	}
}
