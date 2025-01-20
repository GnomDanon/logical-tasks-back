package ru.tasks.logical.task.mapper;

import ru.tasks.logical.document.mapper.DocumentMapper;
import ru.tasks.logical.task.dto.TaskInfo;
import ru.tasks.logical.task.entity.Task;
import ru.tasks.logical.user.mapper.UserMapper;

public class TaskMapper {

    public static TaskInfo mapTaskToTaskInfo(Task task) {
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setId(task.getId());
        taskInfo.setName(task.getName());
        taskInfo.setAuthor(UserMapper.mapUserToUserInfo(task.getAuthor()));
        taskInfo.setTaskType(task.getTaskType());
        taskInfo.setMaxScore(task.getMaxScore());
        taskInfo.setDocumentInfo(DocumentMapper.mapDocumentToDocumentInfo(task.getDocumentBasedOn()));
        taskInfo.setCreatedAt(task.getCreatedAt());
        return taskInfo;
    }
}
