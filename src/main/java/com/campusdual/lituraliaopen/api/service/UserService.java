package com.campusdual.lituraliaopen.api.service;

import com.campusdual.lituraliaopen.api.mapper.dtos.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public interface UserService {

    Page<UserDTO> getAllUsers(Pageable pageable);

    UserDTO getUserById(String username) throws ResourceNotFoundException;

    UserDTO createUser(UserDTO userDto);

    UserDTO updateUser(UserDTO userDto);

    UserDTO updateUser(Integer user_id, UserDTO userDto);

    void deleteUserById(Integer user_id) throws ResourceNotFoundException;


}
