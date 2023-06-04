package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.w3c.dom.stylesheets.LinkStyle;
import peaksoft.dto.response.task.TaskResponse;
import peaksoft.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select new peaksoft.dto.response.task.TaskResponse(t.id,t.taskName,t.taskText,t.deadLine) from Task t")
    List<TaskResponse> findAllTask();

    @Query("select new peaksoft.dto.response.task.TaskResponse(t.id,t.taskName,t.taskText,t.deadLine) from Task t where t.id=:id")
    Optional<TaskResponse> findTaskById(Long id);
}