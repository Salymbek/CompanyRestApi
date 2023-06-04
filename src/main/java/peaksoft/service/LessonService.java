package peaksoft.service;

import peaksoft.dto.request.lesson.LessonRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.lesson.LessonResponse;

import java.util.List;

public interface LessonService {
    List<LessonResponse> findAll();
    LessonResponse getById (Long id);
    SimpleResponse saveLesson(Long courseId,LessonRequest request);
    SimpleResponse deleteLesson(Long id);
    SimpleResponse update(Long id, LessonRequest request);
}
