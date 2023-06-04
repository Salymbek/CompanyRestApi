package peaksoft.dto.response.course;

import java.time.LocalDate;

public record CourseResponse(
        Long id,
        String courseName,
        LocalDate dateOfStart,
        String description
) {
}
