package com.campusdual.lituraliaopen.api.mapper;

import com.campusdual.lituraliaopen.api.mapper.dtos.UserDTO;
import com.campusdual.lituraliaopen.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDto);

}
