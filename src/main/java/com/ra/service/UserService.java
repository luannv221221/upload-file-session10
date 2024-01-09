package com.ra.service;

import com.ra.dto.request.UserRequestDTO;
import com.ra.dto.response.UserResponseDTO;
import com.ra.model.User;

public interface UserService {
    User register(User user);
    UserResponseDTO login(UserRequestDTO userRequestDTO);
}
