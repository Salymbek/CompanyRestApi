package peaksoft.service;

import peaksoft.dto.request.student.ApplicationRequest;
import peaksoft.dto.request.student.RegisterRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.student.StudentResponse;

import java.util.List;

public interface StudentService {
    SimpleResponse saveUser(RegisterRequest request);
    List<StudentResponse> findAll();
    List<StudentResponse> getAllApplications();
    SimpleResponse applicationAccept(Long restId, ApplicationRequest request);
    List<StudentResponse> getFilter(String studyFormat);
    SimpleResponse update(Long id, RegisterRequest request);
    SimpleResponse deleteById(Long id);
    StudentResponse findByStudentId(Long id);

}
