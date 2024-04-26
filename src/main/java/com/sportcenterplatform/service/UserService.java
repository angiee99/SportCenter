package com.sportcenterplatform.service;

import com.sportcenterplatform.dto.UserDTO;
import com.sportcenterplatform.dto.UserInfoDTO;
import com.sportcenterplatform.dto.UserRegisterDTO;

import java.util.List;

/**
 * Service interface for managing users.
 */
public interface UserService {
    UserDTO login(String email, String password);
    Long register(UserRegisterDTO userRegisterDTO);
    List<UserDTO> getUsersById(List<Long> usersIds);
    List<UserInfoDTO> getAllUsers();
}
