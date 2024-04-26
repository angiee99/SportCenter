package com.sportcenterplatform.mapper;

import com.sportcenterplatform.entity.User;
import com.sportcenterplatform.dto.UserDTO;
import com.sportcenterplatform.dto.UserInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for converting between User and UserDTO objects.
 * This interface uses MapStruct library for automatic mapping between entities and DTOs.
 */
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toUserDTO(User user);

    UserInfoDTO toUserInfoDTO(User user);
}
