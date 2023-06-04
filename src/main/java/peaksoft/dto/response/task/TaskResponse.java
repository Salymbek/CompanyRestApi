package peaksoft.dto.response.task;

import java.time.LocalDate;

public record TaskResponse(
        Long id,
        String taskName,
        String taskText,
        LocalDate deadLine
) {
}
