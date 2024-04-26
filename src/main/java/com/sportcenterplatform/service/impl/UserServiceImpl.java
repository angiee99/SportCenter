package com.sportcenterplatform.service.impl;

import com.sportcenterplatform.dto.UserDTO;
import com.sportcenterplatform.dto.UserInfoDTO;
import com.sportcenterplatform.dto.UserRegisterDTO;
import com.sportcenterplatform.entity.Role;
import com.sportcenterplatform.entity.User;
import com.sportcenterplatform.mapper.UserMapper;
import com.sportcenterplatform.repository.UserRepository;
import com.sportcenterplatform.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the UserService interface that handles user-related operations.
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructs a new UserServiceImpl instance.
     *
     * @param userRepository   The repository for user entities.
     * @param passwordEncoder  The encoder for password hashing.
     */
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Logs in a user with the provided email and password.
     *
     * @param email     The email of the user to log in.
     * @param password  The password of the user to log in.
     * @return The DTO representing the logged-in user.
     * @throws EntityNotFoundException  If the user with the provided email is not found.
     * @throws BadCredentialsException  If the provided password does not match the user's password.
     */
    public UserDTO login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with Email: " + email));

        if (passwordEncoder.matches(password, user.getPassword())) {
            return UserMapper.INSTANCE.toUserDTO(user);
        } else {
            throw new BadCredentialsException("Invalid email/password combination.");
        }
    }

    /**
     * Registers a new user with the provided registration information.
     *
     * @param userRegisterDTO  The DTO containing registration information.
     * @return The ID of the newly registered user.
     * @throws IllegalStateException   If a user with the provided email already exists.
     * @throws EntityNotFoundException  If the 'User' role is not found.
     */
    public Long register(UserRegisterDTO userRegisterDTO) {
        if(userRepository.findByEmail(userRegisterDTO.email()).isPresent()) {
            throw new IllegalStateException("User with email " + userRegisterDTO.email() + " already exists");
        }

        String encodedPassword = passwordEncoder.encode(userRegisterDTO.password());
        User user = new User(userRegisterDTO.email(), userRegisterDTO.username(), Role.USER, encodedPassword);

        return userRepository.save(user).getId();
    }

    /**
     * Retrieves users by their IDs.
     *
     * @param usersIds  The IDs of the users to retrieve.
     * @return A list of DTOs representing the retrieved users.
     */
    @Override
    public List<UserDTO> getUsersById(List<Long> usersIds) {
        List<User> users = usersIds.stream()
                .map((id) -> userRepository.findUserById(id).orElse(null))
                .toList();

        return users
                .stream()
                .map(UserMapper.INSTANCE::toUserDTO)
                .toList();
    }

    /**
     * Retrieves all users.
     *
     * @return A list of DTOs representing all users.
     */
    @Override
    public List<UserInfoDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper.INSTANCE::toUserInfoDTO).toList();
    }
}
