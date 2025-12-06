package pl.wsb.fitnesstracker.user.internal;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.*;

import java.util.List;

/**
 * REST controller for user management operations.
 * Provides CRUD endpoints and search functionality for users.
 * All endpoints are prefixed with /v1/users.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    /**
     * Retrieves a list of all users with full information.
     *
     * @return list of UserDto containing full user information
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a list of all users with basic information (ID, firstName,
     * lastName).
     *
     * @return list of UserBasicDto containing basic user information
     */
    @GetMapping("/simple")
    public List<UserBasicDto> getAllUsersSimple() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toBasicDto)
                .toList();
    }

    /**
     * Retrieves detailed information about a user by ID.
     *
     * @param id the ID of the user to retrieve
     * @return UserDto containing full user details
     * @throws UserNotFoundException if user with given ID is not found
     */
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Retrieves a user by email address.
     *
     * @param email the email address of the user to retrieve
     * @return UserDto containing full user details
     * @throws UserNotFoundException if user with given email is not found
     */
    @GetMapping("/search/by-email")
    public UserDto getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("User with email=" + email + " was not found"));
    }

    /**
     * Searches users by email address. Returns only ID and email.
     *
     * @param email the email address to search for
     * @return list of UserSearchResultDto containing ID and email
     */
    @GetMapping("/email")
    public List<UserSearchResultDto> searchUsersByEmail(@RequestParam String email) {
        return userService.searchUsersByEmail(email)
                .stream()
                .map(userMapper::toSearchResultDto)
                .toList();
    }

    /**
     * Retrieves a user by first name and last name.
     *
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     * @return UserDto containing full user details (returns first match if multiple
     *         found)
     * @throws UserNotFoundException if no user with given name is found
     */
    @GetMapping("/search/by-name")
    public UserDto getUserByName(@RequestParam String firstName, @RequestParam String lastName) {
        return userService.searchUsersByName(firstName, lastName)
                .stream()
                .findFirst()
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with name=" + firstName + " " + lastName + " was not found"));
    }

    /**
     * Searches users by email fragment (case-insensitive, partial match).
     * Returns only ID and email of matching users.
     *
     * @param emailFragment the email fragment to search for
     * @return list of UserSearchResultDto containing ID and email
     */
    @GetMapping("/search/email")
    public List<UserSearchResultDto> searchUsersByEmail(@RequestParam String emailFragment) {
        return userService.searchUsersByEmail(emailFragment)
                .stream()
                .map(userMapper::toSearchResultDto)
                .toList();
    }

    /**
     * Searches users older than the specified age.
     *
     * @param ageMin the minimum age (exclusive)
     * @return list of UserDto containing full user details
     */
    @GetMapping("/search/age")
    public List<UserDto> searchUsersByAge(@RequestParam int ageMin) {
        return userService.searchUsersByAgeGreaterThan(ageMin)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Searches users older than the specified date.
     *
     * @param time the date to compare against (users born before this date)
     * @return list of UserDto containing full user details
     */
    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(@PathVariable java.time.LocalDate time) {
        long yearsBetween = java.time.temporal.ChronoUnit.YEARS.between(time, java.time.LocalDate.now());
        int ageMin = (int) yearsBetween;
        return userService.searchUsersByAgeGreaterThan(ageMin)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Creates a new user.
     *
     * @param request the create user request containing user details
     * @return ResponseEntity with created UserDto and HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserRequest request) {
        User user = userMapper.toEntity(request);
        User createdUser = userService.createUser(user);
        UserDto userDto = userMapper.toDto(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    /**
     * Updates an existing user.
     * Only provided fields will be updated; null fields remain unchanged.
     *
     * @param id      the ID of the user to update
     * @param request the update user request containing fields to update
     * @return UserDto containing updated user details
     * @throws UserNotFoundException if user with given ID is not found
     */
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest request) {
        User updatedUser = userMapper.toEntity(request);
        User savedUser = userService.updateUser(id, updatedUser);
        return userMapper.toDto(savedUser);
    }

    /**
     * Deletes a user by ID.
     *
     * @param id the ID of the user to delete
     * @return ResponseEntity with HTTP 204 No Content status
     * @throws UserNotFoundException if user with given ID is not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
