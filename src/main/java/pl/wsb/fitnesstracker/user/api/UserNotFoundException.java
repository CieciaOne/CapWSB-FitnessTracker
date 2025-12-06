package pl.wsb.fitnesstracker.user.api;

import pl.wsb.fitnesstracker.exception.api.NotFoundException;

/**
 * Exception indicating that the {@link User} was not found.
 */
@SuppressWarnings("squid:S110")
public class UserNotFoundException extends NotFoundException {

    /**
     * Creates a UserNotFoundException with a custom message.
     *
     * @param message the error message
     */
    public UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Creates a UserNotFoundException for a user with the given ID.
     *
     * @param id the ID of the user that was not found
     */
    public UserNotFoundException(Long id) {
        this("User with ID=%s was not found".formatted(id));
    }

}
