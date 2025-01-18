package ru.tasks.logical.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tasks.logical.task.converter.CrosswordAttributeConverter;
import ru.tasks.logical.task.converter.CrosswordConverter;
import ru.tasks.logical.task.dto.Task;
import ru.tasks.logical.task.dto.TaskInfo;
import ru.tasks.logical.task.dto.TaskTypeInfo;
import ru.tasks.logical.task.entity.Crossword;
import ru.tasks.logical.task.entity.TaskType;
import ru.tasks.logical.task.exception.TaskNotExistsException;
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
    private final CrosswordConverter crosswordConverter = new CrosswordConverter();
    private final CrosswordAttributeConverter crosswordAttributeConverter = new CrosswordAttributeConverter();

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

    public Task getById(UUID taskId) throws TaskNotExistsException {
        ru.tasks.logical.task.entity.Task task = taskRepository.findById(taskId).orElseThrow(TaskNotExistsException::new);

        TaskInfo taskInfo = TaskMapper.mapTaskToTaskInfo(task);
        String content = "";
        TaskType taskType = task.getTaskType();

        switch (taskType) {
            case TEST -> content = task.getContent();
            case CROSSWORD -> {
                String taskContent = task.getContent();
                Crossword crossword = crosswordAttributeConverter.convertToEntityAttribute(taskContent);
                content = crosswordConverter.convertToString(crossword);
            }
        }

        return new Task().setTaskInfo(taskInfo).setContent(content);
    }
}
