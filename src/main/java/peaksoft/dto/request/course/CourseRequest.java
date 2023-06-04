package peaksoft.dto.request.course;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.NonNull;

import java.time.LocalDate;

public record CourseRequest(
        @NotEmpty(message = "course name can't be empty!")
        String courseName,
        @Past(message = "the date of start must be past!")
        LocalDate dateOfStart,
        @NotEmpty(message = "description can't be empty!")
        String description
) {
}
