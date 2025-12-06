package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

/**
 * Data Transfer Object representing user search result by email.
 * Contains only ID and email of the user.
 *
 * @param id    The unique identifier of the user
 * @param email The email address of the user
 */
public record UserSearchResultDto(
        @Nullable Long id,
        String email
) {
}

