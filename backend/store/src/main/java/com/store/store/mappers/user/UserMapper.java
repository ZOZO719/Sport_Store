package com.store.store.mappers.user;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.store.store.dtos.user.CreateUserRequest;
import com.store.store.dtos.user.UpdateUserRequest;
import com.store.store.dtos.user.UserDto;
import com.store.store.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
     UserDto toDto(User user);
    User toEntitiy (CreateUserRequest userDto);
    void updateUser(UpdateUserRequest updateUserRequest ,@MappingTarget User user );
}
