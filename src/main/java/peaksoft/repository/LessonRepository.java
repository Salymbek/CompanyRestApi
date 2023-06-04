package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.response.lesson.LessonResponse;
import peaksoft.model.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query("select new peaksoft.dto.response.lesson.LessonResponse(l.id,l.lessonName) from Lesson l")
    List<LessonResponse> findAllLesson();

    @Query("select new peaksoft.dto.response.lesson.LessonResponse(l.id,l.lessonName) from Lesson l where l.id=:id")
    Optional<LessonResponse> findByLessonId(Long id);


}