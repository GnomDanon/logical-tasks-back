package ru.tasks.logical.task.mapper;

import ru.tasks.logical.task.dto.TaskSolverInfo;
import ru.tasks.logical.task.entity.TaskSolver;
import ru.tasks.logical.user.mapper.UserMapper;

public class TaskSolverMapper {

    public static TaskSolverInfo mapTaskSolverToTaskSolverInfo(TaskSolver taskSolver) {
        TaskSolverInfo taskSolverInfo = new TaskSolverInfo();
        taskSolverInfo.setId(taskSolver.getId());
        taskSolverInfo.setSolver(UserMapper.mapUserToUserInfo(taskSolver.getSolver()));
        taskSolverInfo.setTaskInfo(TaskMapper.mapTaskToTaskInfo(taskSolver.getTask()));
        taskSolverInfo.setScore(taskSolver.getScore());
        taskSolverInfo.setSolved(taskSolver.isSolved());
        return taskSolverInfo;
    }
}
