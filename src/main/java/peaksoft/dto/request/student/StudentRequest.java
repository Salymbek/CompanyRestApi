package peaksoft.dto.request.student;

import lombok.Builder;
import peaksoft.enums.StudyFormat;
@Builder
public record StudentRequest(
        String firstName,
        String lastName,
        String phoneNumber,

        String email,
        StudyFormat studyFormat
) {
}
