package peaksoft.dto.request.company;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record CompanyRequest(
        @NotEmpty(message = "name can't be empty!")
        String name,
        @Column(unique = true)
        @NotEmpty(message = "country can't be empty!")
        String country,
        @NotEmpty(message = "Address can't be empty!")
        String address,
        @NotEmpty(message = "phoneNumber cannot be empty!")
        @Pattern(regexp = "\\+996\\d{9}", message = "Invalid phone number format!")
        String phoneNumber
) {
}
