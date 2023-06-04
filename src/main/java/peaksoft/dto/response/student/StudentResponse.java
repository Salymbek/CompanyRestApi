package peaksoft.dto.response.student;

import lombok.Builder;
import peaksoft.enums.StudyFormat;
@Builder
public record StudentResponse(
        Long id,
        String fullName,
        String phoneNumber,
        String email,
        StudyFormat studyFormat,
        Boolean isBlocked
) {
}
