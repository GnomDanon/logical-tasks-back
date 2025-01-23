package ru.tasks.logical.task.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.tasks.logical.task.generator.dto.questions.Question;

@Data
@Accessors(chain = true)
@Schema(description = "Задача")
public class Task {
	TaskInfo taskInfo;
	Question[] question;
	String content;
}
