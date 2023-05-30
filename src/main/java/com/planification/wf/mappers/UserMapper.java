package com.planification.wf.mappers;

import com.planification.wf.models.DTO.UserDTO;
import com.planification.wf.models.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "role", target = "role")
    UserDTO toDto(User user);

    List<UserDTO> toListDto(List<User> users);
}
