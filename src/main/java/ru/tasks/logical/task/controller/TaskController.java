package ru.tasks.logical.task.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tasks.logical.task.dto.CreateTaskSolverRequest;
import ru.tasks.logical.task.dto.Task;
import ru.tasks.logical.task.dto.TaskInfo;
import ru.tasks.logical.task.dto.TaskSolverInfo;
import ru.tasks.logical.task.dto.TaskTypeInfo;
import ru.tasks.logical.task.exception.TaskNotExistsException;
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

    @Operation(summary = "Получение задания")
    @GetMapping("/get/{taskId}")
    public ResponseEntity<Task> getById(@PathVariable UUID taskId) {
        try {
            return ResponseEntity.ok(taskService.getById(taskId));
        } catch (TaskNotExistsException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Получение доступных типов задач")
    @GetMapping("/types")
    public List<TaskTypeInfo> getTaskTypes() {
        return taskService.getTaskTypes();
    }

    @Operation(summary = "Получение всех студентов, решающих заданное задание")
    @GetMapping("/taskSolvers/{taskId}")
    public ResponseEntity<List<TaskSolverInfo>> getTaskSolvers(@PathVariable UUID taskId) {
        try {
            return ResponseEntity.ok(taskSolverService.getTaskSolversByTaskId(taskId));
        } catch (TaskNotExistsException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Фиксация решения задачи")
    @PostMapping("/taskSolvers/create")
    public ResponseEntity<TaskSolverInfo> createTaskSolver(@RequestBody CreateTaskSolverRequest request) {
        try {
            return ResponseEntity.ok(taskSolverService.createTaskSolverInfo(request));
        } catch (TaskNotExistsException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
