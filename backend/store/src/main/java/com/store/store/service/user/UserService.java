package com.store.store.service.user;

import java.util.List;

import com.store.store.dtos.user.CreateUserRequest;
import com.store.store.dtos.user.UpdateUserRequest;
import com.store.store.dtos.user.UserDto;

public interface UserService {
    public List<UserDto> getAllUsers();
    public UserDto getUserById(Long Id);
    public UserDto createUser(CreateUserRequest user);
    public UserDto updateUser(Long id , UpdateUserRequest updateUserRequest);
    public void deleteUser(Long id);

}
