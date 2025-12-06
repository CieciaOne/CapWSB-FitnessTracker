package pl.wsb.fitnesstracker.user.api;

import java.util.List;
import java.util.Optional;

/**
 * Interface (API) for querying operations on {@link User} entities.
 * Provides methods for retrieving and searching users without modifying them.
 */
public interface UserProvider {

    /**
     * Retrieves a user based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be
     * returned.
     *
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located user, or
     *         {@link Optional#empty()} if not found
     */
    Optional<User> getUser(Long userId);

    /**
     * Retrieves a user based on their email.
     * If the user with given email is not found, then {@link Optional#empty()} will
     * be returned.
     *
     * @param email The email of the user to be searched
     * @return An {@link Optional} containing the located user, or
     *         {@link Optional#empty()} if not found
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return A list containing all users
     */
    List<User> findAllUsers();

    /**
     * Searches users by email fragment (case-insensitive, partial match).
     *
     * @param emailFragment the email fragment to search for
     * @return list of users matching the email fragment
     */
    List<User> searchUsersByEmail(String emailFragment);

    /**
     * Searches users by first name and last name.
     *
     * @param firstName the first name to search for
     * @param lastName  the last name to search for
     * @return list of users matching the first and last name
     */
    List<User> searchUsersByName(String firstName, String lastName);

    /**
     * Searches users older than the specified age.
     *
     * @param minAge the minimum age (exclusive)
     * @return list of users older than the specified age
     */
    List<User> searchUsersByAgeGreaterThan(int minAge);

}
