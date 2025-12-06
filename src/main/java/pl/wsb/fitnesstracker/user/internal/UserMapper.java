package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.CreateUserRequest;
import pl.wsb.fitnesstracker.user.api.UpdateUserRequest;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserBasicDto;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserSearchResultDto;

/**
 * Mapper class for converting between User entities and DTOs.
 * Provides methods for mapping User objects to various DTO representations.
 */
@Component
class UserMapper {

    /**
     * Converts a User entity to a full UserDto.
     *
     * @param user the user entity to convert
     * @return UserDto containing all user information
     */
    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    /**
     * Converts a User entity to a UserBasicDto (ID, firstName, lastName only).
     *
     * @param user the user entity to convert
     * @return UserBasicDto containing basic user information
     */
    UserBasicDto toBasicDto(User user) {
        return new UserBasicDto(user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    /**
     * Converts a User entity to a UserSearchResultDto (ID, email only).
     *
     * @param user the user entity to convert
     * @return UserSearchResultDto containing search result information
     */
    UserSearchResultDto toSearchResultDto(User user) {
        return new UserSearchResultDto(user.getId(), user.getEmail());
    }

    /**
     * Converts a CreateUserRequest to a User entity.
     *
     * @param request the create user request
     * @return User entity ready to be persisted
     */
    User toEntity(CreateUserRequest request) {
        return new User(
                request.firstName(),
                request.lastName(),
                request.birthdate(),
                request.email()
        );
    }

    /**
     * Converts an UpdateUserRequest to a User entity (for partial updates).
     *
     * @param request the update user request
     * @return User entity with updated fields (null fields remain unchanged)
     */
    User toEntity(UpdateUserRequest request) {
        return new User(
                request.firstName(),
                request.lastName(),
                request.birthdate(),
                request.email()
        );
    }
}
