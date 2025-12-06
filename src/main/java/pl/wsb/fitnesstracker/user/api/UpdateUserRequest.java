package pl.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;

/**
 * Data Transfer Object for updating an existing user.
 * All fields are optional - only provided fields will be updated.
 *
 * @param firstName The first name of the user (optional)
 * @param lastName  The last name of the user (optional)
 * @param birthdate The birthdate of the user (optional)
 * @param email     The email address of the user (optional, must be valid email format if provided)
 */
public record UpdateUserRequest(
        @Nullable String firstName,
        @Nullable String lastName,
        @Nullable @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
        @Nullable @Email(message = "Email must be valid") String email
) {
}

