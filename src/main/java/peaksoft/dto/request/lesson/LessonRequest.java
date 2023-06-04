package peaksoft.dto.request.lesson;

import jakarta.validation.constraints.NotEmpty;

public record LessonRequest(
        @NotEmpty(message = "lesson name can't be empty!")
        String lessonName
) {
}
