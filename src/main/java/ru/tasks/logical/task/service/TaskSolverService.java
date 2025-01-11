package ru.tasks.logical.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tasks.logical.task.dto.TaskInfo;
import ru.tasks.logical.task.dto.TaskSolverInfo;
import ru.tasks.logical.task.entity.Task;
import ru.tasks.logical.task.entity.TaskSolver;
import ru.tasks.logical.task.mapper.TaskMapper;
import ru.tasks.logical.task.mapper.TaskSolverMapper;
import ru.tasks.logical.task.repository.TaskSolverRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskSolverService {

    private final TaskSolverRepository taskSolverRepository;

    public List<TaskInfo> getTasksByTaskSolverId(Long solverId) {
        return taskSolverRepository.findAllTasksBySolverId(solverId)
                .stream()
                .map(TaskMapper::mapTaskToTaskInfo)
                .collect(Collectors.toList());
    }

    public List<TaskSolverInfo> getTaskSolversByTaskId(Long taskId) {
        return taskSolverRepository.findAllByTaskId(taskId)
                .stream()
                .map(TaskSolverMapper::mapTaskSolverToTaskSolverInfo)
                .collect(Collectors.toList());
    }
}
