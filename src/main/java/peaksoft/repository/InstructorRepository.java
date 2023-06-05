package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.dto.response.instructor.InstructorResponse;
import peaksoft.model.Instructor;

import java.util.List;
import java.util.Optional;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    @Query("select new peaksoft.dto.response.instructor.InstructorResponse(i.id,concat(i.firstName,' ',i.lastName),i.phoneNumber,i.specialization)from Instructor i")
    List<InstructorResponse> findAllInstructors();

    @Query("select new peaksoft.dto.response.instructor.InstructorResponse(i.id,concat(i.firstName,' ',i.lastName),i.phoneNumber,i.specialization)from Instructor i where i.id=:id")
    Optional<InstructorResponse> findByIdInst(Long id);

    @Query("select (count (s)) from Instructor i join i.courses c join c.groups g join g.students s where i.id = :instructorId")
    int getAllCount(@Param("instructorId") Long instructorId);

    @Query("select cast( count(s) as int ) from Instructor i join i.courses c join  c.groups g join g.students s where i.id=:instructorId")
    int getAllStudentCount(Long instructorId);
    @Query("select g.groupName from Group g join g.courses c join c.instructor i where i.id = ?1")
    List<String> getAllInstructorsDetails(Long id);
    boolean existsByPhoneNumber(String phoneNumber);

}