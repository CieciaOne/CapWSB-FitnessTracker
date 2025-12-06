package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

/**
 * Data Transfer Object representing basic user information.
 * Contains only ID, first name, and last name of the user.
 *
 * @param id        The unique identifier of the user
 * @param firstName The first name of the user
 * @param lastName  The last name of the user
 */
public record UserBasicDto(
        @Nullable Long id,
        String firstName,
        String lastName
) {
}

