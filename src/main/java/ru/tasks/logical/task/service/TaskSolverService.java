package ru.tasks.logical.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tasks.logical.task.dto.CreateTaskSolverRequest;
import ru.tasks.logical.task.dto.TaskSolverInfo;
import ru.tasks.logical.task.entity.Task;
import ru.tasks.logical.task.entity.TaskSolver;
import ru.tasks.logical.task.exception.TaskNotExistsException;
import ru.tasks.logical.task.repository.TaskRepository;
import ru.tasks.logical.task.repository.TaskSolverRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskSolverService {

    private final TaskSolverRepository taskSolverRepository;
    private final TaskRepository taskRepository;

    public List<TaskSolverInfo> getTaskSolversByTaskId(UUID taskId) throws TaskNotExistsException {
        Task task = taskRepository.findById(taskId).orElseThrow(TaskNotExistsException::new);
        return taskSolverRepository.findAllByTaskId(taskId)
                .stream()
                .map(taskSolver -> new TaskSolverInfo()
						.setId(taskSolver.getId())
						.setTaskId(task.getId())
						.setFirstName(taskSolver.getFirstName())
						.setLastName(taskSolver.getLastName())
						.setScore(taskSolver.getScore())
						.setMaxScore(task.getMaxScore()))
                .collect(Collectors.toList());
    }

    public TaskSolverInfo createTaskSolverInfo(CreateTaskSolverRequest request) throws TaskNotExistsException {
        String firstName = request.getFirstName();
        String lastName = request.getLastName();
        int score = request.getScore();
        UUID taskId = request.getTaskId();

        Task task = taskRepository.findById(taskId).orElseThrow(TaskNotExistsException::new);
        int maxScore = task.getMaxScore();

        TaskSolver taskSolver = new TaskSolver()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setScore(score)
                .setTask(task);

        taskSolver = taskSolverRepository.save(taskSolver);

		return new TaskSolverInfo()
                .setId(taskSolver.getId())
                .setTaskId(task.getId())
                .setFirstName(taskSolver.getFirstName())
                .setLastName(taskSolver.getLastName())
                .setScore(taskSolver.getScore())
                .setMaxScore(maxScore);
    }
}
