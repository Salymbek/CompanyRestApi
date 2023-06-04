package peaksoft.dto.request.student;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record ApplicationRequest(
        @NonNull
        Long studentId,
        @NonNull
        Boolean accept
) {
}
