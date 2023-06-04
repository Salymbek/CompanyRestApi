package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.response.course.CourseResponse;
import peaksoft.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select new peaksoft.dto.response.course.CourseResponse(c.id,c.courseName,c.dateOfStart,c.description)from Course c")
    List<CourseResponse> findAllCourse ();

    @Query("select new peaksoft.dto.response.course.CourseResponse(c.id,c.courseName,c.dateOfStart,c.description)from Course c where c.id=:id")
    Optional<CourseResponse> findByCourseId (Long id);

    @Query("select new peaksoft.dto.response.course.CourseResponse(c.id,c.courseName,c.dateOfStart,c.description)from Course c order by c.dateOfStart")
    List<CourseResponse> ascSorting();
    @Query("select new peaksoft.dto.response.course.CourseResponse(c.id,c.courseName,c.dateOfStart,c.description)from Course c order by c.dateOfStart desc ")
    List<CourseResponse> descSorting();


}