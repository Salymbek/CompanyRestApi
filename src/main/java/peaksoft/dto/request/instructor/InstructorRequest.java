package peaksoft.dto.request.instructor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record InstructorRequest(
        @NotEmpty(message = "first name can't be empty!")
        String firstName,
        @NotEmpty(message = "last name can't be empty!")
        String lastName,
        @NotEmpty(message = "phone number can't be empty!")
        @Pattern(regexp = "\\+996\\d{9}", message = "Invalid phone number format!")
        String phoneNumber,
        @NotEmpty(message = "the specialization can't be empty!")
        String specialization
) {
}
