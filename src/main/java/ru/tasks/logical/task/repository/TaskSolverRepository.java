package ru.tasks.logical.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tasks.logical.task.entity.TaskSolver;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskSolverRepository extends JpaRepository<TaskSolver, UUID> {
    List<TaskSolver> findAllByTaskId(UUID taskId);
}
