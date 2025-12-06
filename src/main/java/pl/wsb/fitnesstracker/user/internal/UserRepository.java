package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Repository interface for {@link User} entity operations.
 * Provides methods for querying and managing users in the database.
 */
interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if
     *         none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    /**
     * Searches users by email fragment (case-insensitive, partial match).
     *
     * @param emailFragment the email fragment to search for
     * @return list of users matching the email fragment
     */
    default List<User> findByEmailContainingIgnoreCase(String emailFragment) {
        return findAll().stream()
                .filter(user -> user.getEmail() != null
                        && user.getEmail().toLowerCase().contains(emailFragment.toLowerCase()))
                .toList();
    }

    /**
     * Searches users by first name and last name (exact match, case-sensitive).
     *
     * @param firstName the first name to search for
     * @param lastName  the last name to search for
     * @return list of users matching the first and last name
     */
    default List<User> findByFirstNameAndLastName(String firstName, String lastName) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getFirstName(), firstName)
                        && Objects.equals(user.getLastName(), lastName))
                .toList();
    }

    /**
     * Searches users older than the specified age.
     *
     * @param minAge the minimum age (exclusive)
     * @return list of users older than the specified age
     */
    default List<User> findByAgeGreaterThan(int minAge) {
        LocalDate maxBirthdate = LocalDate.now().minusYears(minAge + 1);
        return findAll().stream()
                .filter(user -> user.getBirthdate() != null
                        && user.getBirthdate().isBefore(maxBirthdate))
                .toList();
    }

}
