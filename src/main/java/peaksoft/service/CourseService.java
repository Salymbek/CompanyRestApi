package peaksoft.service;

import peaksoft.dto.request.course.CourseRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.course.CourseResponse;

import java.util.List;

public interface CourseService {
    List<CourseResponse> findAll();
    CourseResponse getById (Long id);
    SimpleResponse saveCourse(Long companyId,CourseRequest request);
    SimpleResponse deleteCourse(Long id);
    SimpleResponse update(Long id, CourseRequest request);

    List<CourseResponse> sorting(String ascOrDesc);
}
