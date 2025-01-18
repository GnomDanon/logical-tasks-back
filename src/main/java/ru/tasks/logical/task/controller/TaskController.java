package ru.tasks.logical.task.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tasks.logical.task.dto.TaskInfo;
import ru.tasks.logical.task.dto.TaskSolverInfo;
import ru.tasks.logical.task.dto.TaskTypeInfo;
import ru.tasks.logical.task.service.TaskService;
import ru.tasks.logical.task.service.TaskSolverService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@Tag(name = "Задача")
public class TaskController {

    private final TaskService taskService;

    private final TaskSolverService taskSolverService;

    @Operation(summary = "Получение задач, сгенерированных заданных автором")
    @GetMapping("/author/{authorId}")
    public List<TaskInfo> getByAuthorId(@PathVariable UUID authorId) {
        return taskService.getTasksByAuthorId(authorId);
    }

    @Operation(summary = "Получение доступных типов задач")
    @GetMapping("/types")
    public List<TaskTypeInfo> getTaskTypes() {
        return taskService.getTaskTypes();
    }

    @Operation(summary = "Получение всех студентов, решающих заданное задание")
    @GetMapping("/taskSolvers/{taskId}")
    public List<TaskSolverInfo> getTaskSolvers(@PathVariable UUID taskId) {
        return taskSolverService.getTaskSolversByTaskId(taskId);
    }
}
