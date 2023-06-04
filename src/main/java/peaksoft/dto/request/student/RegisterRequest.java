package peaksoft.dto.request.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.NonNull;
import peaksoft.enums.StudyFormat;
@Builder
public record RegisterRequest(
        @NotEmpty(message = "first name can't be empty!")
        String firstName,
        @NotEmpty(message = "last name can't be empty!")
        String lastName,
        @NotEmpty(message = "phone number can't be empty!")
        @Pattern(regexp = "\\+996\\d{9}",message = "Invalid phone number format!")
        String phoneNumber,
        @NotEmpty(message = "email can't be empty!")
        @Email(message = "Invalid email")
        String email,
        @NonNull
        StudyFormat studyFormat
) {
}
