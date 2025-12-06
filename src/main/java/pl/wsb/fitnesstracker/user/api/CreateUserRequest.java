package pl.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * Data Transfer Object for creating a new user.
 * Represents the request body for user creation endpoint.
 *
 * @param firstName The first name of the user (required)
 * @param lastName  The last name of the user (required)
 * @param birthdate The birthdate of the user (required)
 * @param email     The email address of the user (required, must be valid email format)
 */
public record CreateUserRequest(
        @NotBlank(message = "First name is required")
        String firstName,
        @NotBlank(message = "Last name is required")
        String lastName,
        @NotNull(message = "Birthdate is required")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate birthdate,
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        String email
) {
}

