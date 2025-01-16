package ru.tasks.logical.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.tasks.logical.task.entity.Task;
import ru.tasks.logical.task.entity.TaskSolver;

import java.util.List;

@Repository
public interface TaskSolverRepository extends JpaRepository<TaskSolver, Long> {

    @Query("select t from Task t where t.id in (select ts.task.id from TaskSolver ts where ts.solver.id = :solverId)")
    List<Task> findAllTasksBySolverId(@Param("solverId") Long solverId);

    List<TaskSolver> findAllByTaskId(Long taskId);
}