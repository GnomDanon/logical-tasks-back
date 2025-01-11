package ru.tasks.logical.task.mapper;

import ru.tasks.logical.task.dto.TaskTypeInfo;
import ru.tasks.logical.task.entity.TaskType;

public class TaskTypeMapper {

    public static TaskTypeInfo mapTaskTypeToTaskTypeInfo(TaskType taskType) {
        TaskTypeInfo taskTypeInfo = new TaskTypeInfo();
        taskTypeInfo.setTaskTypeName(taskType.getName());
        taskTypeInfo.setImageUri(taskType.getImageUri());
        return taskTypeInfo;
    }
}
