package com.store.store.serviceImplementation.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.store.store.dtos.user.CreateUserRequest;
import com.store.store.dtos.user.UpdateUserRequest;
import com.store.store.dtos.user.UserDto;
import com.store.store.entities.User;
import com.store.store.mappers.user.UserMapper;
import com.store.store.repository.UserRepo;
import com.store.store.service.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserSerImp implements UserService {
    private final UserRepo userRepo ;
    private final UserMapper userMapper ;
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map(userMapper::toDto).toList();
        }
    @Override
    public UserDto getUserById(Long Id) {
        User user = userRepo.findById(Id).orElseThrow(()->new RuntimeException("User Not FOund"));
        return userMapper.toDto(user);
    }
    @Override
    public UserDto createUser(CreateUserRequest createUserRequest) {
        User user = userMapper.toEntitiy(createUserRequest);
        User createdUser = userRepo.save(user);

        return userMapper.toDto(createdUser);
    }
    @Override
    public UserDto updateUser(Long id, UpdateUserRequest updateUserRequest) {
        User user = userRepo.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        userMapper.updateUser(updateUserRequest, user);
        User updatedUser = userRepo.save(user);
        return userMapper.toDto(updatedUser);
    }
    @Override
    public void deleteUser(Long id) {
if (!userRepo.existsById(id)) {
            throw new RuntimeException("Category not found");
        }        userRepo.deleteById(id);
    }

    
}
