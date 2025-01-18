package ru.tasks.logical.task.converter;

import ru.tasks.logical.task.entity.Crossword;
import ru.tasks.logical.task.entity.CrosswordItem;

public class CrosswordConverter {
	private final CrosswordItemAttributeConverter itemConverter = new CrosswordItemAttributeConverter();

	public String convertToString(Crossword crossword) {
		CrosswordItem[] acrossItems = crossword.getAcross();
		CrosswordItem[] downItems = crossword.getDown();
		int index = 1;
		StringBuilder column = new StringBuilder();
		column.append("{");

		column.append("\"across\":{");
		for (CrosswordItem item : acrossItems) {
			column.append(String.format("\"%d\":", index++));
			column.append(itemConverter.convertToDatabaseColumn(item));
			column.append(",");
		}
		column.deleteCharAt(column.length() - 1);
		column.append("},");

		column.append("\"down\":{");
		for (CrosswordItem item: downItems) {
			column.append(String.format("\"%d\":", index++));
			column.append(itemConverter.convertToDatabaseColumn(item));
			column.append(",");
		}
		column.deleteCharAt(column.length() - 1);
		column.append("}");

		column.append("}");

		return column.toString();
	}
}
