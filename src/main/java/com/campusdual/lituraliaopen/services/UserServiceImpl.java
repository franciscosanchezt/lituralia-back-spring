package com.campusdual.lituraliaopen.services;

import com.campusdual.lituraliaopen.api.mapper.UserMapper;
import com.campusdual.lituraliaopen.api.mapper.dtos.UserDTO;
import com.campusdual.lituraliaopen.api.service.UserService;
import com.campusdual.lituraliaopen.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper     = userMapper;
    }

    @Override
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::userToUserDTO);
    }

    @Override
    public UserDTO getUserById(String username) throws ResourceNotFoundException {
        return userRepository.findById(username).map(userMapper::userToUserDTO)
                             .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public UserDTO createUser(UserDTO userDto) {
        return null;
    }

    @Override
    public UserDTO updateUser(UserDTO userDto) {
        return null;
    }

    @Override
    public UserDTO updateUser(Integer user_id, UserDTO userDto) {
        return null;
    }

    @Override
    public void deleteUserById(Integer user_id) throws ResourceNotFoundException {

    }
}
