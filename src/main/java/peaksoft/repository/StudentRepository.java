package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.response.student.StudentResponse;
import peaksoft.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select new peaksoft.dto.response.student.StudentResponse(s.id, concat(s.firstName,' ', s.lastName),s.phoneNumber,s.email,s.studyFormat,s.isBlocked)from Student s where s.isBlocked=false ")
    List<StudentResponse>findAllStudentsIsBlock();

    @Query("select new peaksoft.dto.response.student.StudentResponse(s.id, concat(s.firstName,' ', s.lastName),s.phoneNumber,s.email,s.studyFormat,s.isBlocked)from Student s where s.isBlocked=true ")
    List<StudentResponse>findAllStudentsIsTrue();

    @Query("select new peaksoft.dto.response.student.StudentResponse(s.id, concat(s.firstName,' ', s.lastName),s.phoneNumber,s.email,s.studyFormat,s.isBlocked)from Student s where s.id=:id ")
    Optional<StudentResponse>findByIdStudent(Long id);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    @Query("select new peaksoft.dto.response.student.StudentResponse(s.id, concat(s.firstName,' ', s.lastName),s.phoneNumber,s.email,s.studyFormat,s.isBlocked) from Student s where s.studyFormat='ONLINE'")
    List<StudentResponse> getOnline();
    @Query("select new peaksoft.dto.response.student.StudentResponse(s.id, concat(s.firstName,' ', s.lastName),s.phoneNumber,s.email,s.studyFormat,s.isBlocked) from Student s where s.studyFormat='OFFLINE'")
    List<StudentResponse> getOffline();

}