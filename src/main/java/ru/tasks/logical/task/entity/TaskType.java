package ru.tasks.logical.task.entity;

import java.net.URI;

public enum TaskType {
    CROSSWORD("Кроссворд", "http://localhost:8080/getCrosswordImage");

    TaskType(String name, String imageUri) {
        this.name = name;
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public String getImageUri() {
        return imageUri;
    }

    private final String name;

    private final String imageUri;
}
