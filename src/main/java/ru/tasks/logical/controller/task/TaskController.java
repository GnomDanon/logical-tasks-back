package ru.tasks.logical.controller.task;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tasks.logical.dto.task.CreateTaskSolverRequest;
import ru.tasks.logical.dto.task.TaskGenerateRequest;
import ru.tasks.logical.dto.task.TaskInfo;
import ru.tasks.logical.dto.task.TaskSolver;
import ru.tasks.logical.dto.task.TaskType;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@Tag(name = "Задача")
public class TaskController {

    @Operation(summary = "Генерация задания")
    @PostMapping("/generate")
    public Long generate(@RequestBody @Valid TaskGenerateRequest request) {
        return 0L;
    }

    @Operation(summary = "Получение задач, сгенерированных заданных автором")
    @GetMapping("/author/{authorId}")
    public List<TaskInfo> getByAuthorId(@PathVariable Long authorId) {
        return new ArrayList<>();
    }

    @Operation(summary = "Получение задач, доступных данному студенту")
    @GetMapping("/student/{studentId}")
    public List<TaskInfo> getByStudentId(@PathVariable Long studentId) {
        return new ArrayList<>();
    }

    @Operation(summary = "Получение доступных типов задач")
    @GetMapping("/types")
    public List<TaskType> getTaskTypes() {
        return new ArrayList<>();
    }

    @Operation(summary = "Выдача доступа к решению задачи для студента")
    @PostMapping("/addTaskSolver")
    public Long createTaskSolver(@RequestBody @Valid CreateTaskSolverRequest request) {
        return 0L;
    }

    @Operation(summary = "Получение всех студентов, решающих заданное задание")
    @GetMapping("/taskSolvers/{taskId}")
    public List<TaskSolver> getTaskSolvers(@PathVariable Long taskId) {
        return new ArrayList<>();
    }
}
