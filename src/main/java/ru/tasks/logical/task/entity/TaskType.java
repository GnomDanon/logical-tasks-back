package ru.tasks.logical.task.entity;

import lombok.Getter;

@Getter
public enum TaskType {
    CROSSWORD("CROSSWORD", "http://localhost:8080/getCrosswordImage"),
    TEST("TEST", "http://localhost:8080/getTestImage");

    TaskType(String name, String imageUri) {
        this.name = name;
        this.imageUri = imageUri;
    }

    private final String name;

    private final String imageUri;
}
