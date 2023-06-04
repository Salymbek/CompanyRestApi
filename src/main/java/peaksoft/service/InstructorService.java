package peaksoft.service;

import peaksoft.dto.request.instructor.InstructorRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.group.CountStudentByGroup;
import peaksoft.dto.response.instructor.GroupByInstructorDetails;
import peaksoft.dto.response.instructor.InstructorResponse;

import java.util.List;

public interface InstructorService {
    SimpleResponse saveInstructor(Long courseId, InstructorRequest instructorRequest);
    List<InstructorResponse> getAllInstructors();
    InstructorResponse getInstructorById(Long instructorId);
    SimpleResponse updateInstructor(Long instructorId,InstructorRequest instructorRequest);

    SimpleResponse deleteInstructor(Long instructorId);
    SimpleResponse assignInstructorToCompany(Long instructorId, Long companyId);
    CountStudentByGroup get(Long id);
    GroupByInstructorDetails infoInstructor(Long instructorId);
}
