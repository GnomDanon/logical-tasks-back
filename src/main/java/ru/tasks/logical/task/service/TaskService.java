package ru.tasks.logical.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tasks.logical.task.dto.TaskInfo;
import ru.tasks.logical.task.dto.TaskTypeInfo;
import ru.tasks.logical.task.entity.TaskType;
import ru.tasks.logical.task.mapper.TaskMapper;
import ru.tasks.logical.task.mapper.TaskTypeMapper;
import ru.tasks.logical.task.repository.TaskRepository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskInfo> getTasksByAuthorId(UUID authorId) {
        return taskRepository.findAllByAuthor_Id(authorId)
                .stream()
                .map(TaskMapper::mapTaskToTaskInfo)
                .collect(Collectors.toList());
    }

    public List<TaskTypeInfo> getTaskTypes() {
        return Arrays.stream(TaskType.values())
                .map(TaskTypeMapper::mapTaskTypeToTaskTypeInfo)
                .toList();
    }
}
