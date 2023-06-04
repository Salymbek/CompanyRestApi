package peaksoft.dto.request.task;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record TaskRequest(
        @NotEmpty(message = "task name can't be empty!")
        String taskName,
        @NotEmpty(message = "text can't be empty!")
        String taskText,
        @Future(message = "the deadline must be in tne future!")
        LocalDate deadLine
) {
}
