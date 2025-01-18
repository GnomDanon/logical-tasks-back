package ru.tasks.logical.task.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tasks.logical.task.converter.CrosswordConverter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Crossword {
	private CrosswordItem[] across;
	private CrosswordItem[] down;

	@Override
	public String toString() {
		return new CrosswordConverter().convertToString(this);
	}
}
