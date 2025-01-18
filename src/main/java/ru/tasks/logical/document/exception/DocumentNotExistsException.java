package ru.tasks.logical.document.exception;

public class DocumentNotExistsException extends Exception {
	public DocumentNotExistsException() {
		super("Документ не найден");
	}
}
