package com.store.store.controllers.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.store.store.dtos.user.CreateUserRequest;
import com.store.store.dtos.user.UpdateUserRequest;
import com.store.store.dtos.user.UserDto;
import com.store.store.serviceImplementation.user.UserSerImp;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserSerImp userSerImp;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> users = userSerImp.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        try {
                    UserDto userDto = userSerImp.getUserById(id);
                    return ResponseEntity.ok(userDto);

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest createUserRequest , UriComponentsBuilder uriComponentsBuilder){
        UserDto user = userSerImp.createUser(createUserRequest);
        var uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(user.getEmail()).toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UpdateUserRequest updateUserRequest , @PathVariable Long id){
        try {
            UserDto userDto = userSerImp.updateUser(id, updateUserRequest);
            return ResponseEntity.ok(userDto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") Long id){
    try {
        userSerImp.deleteUser(id);
        return ResponseEntity.ok().build();
    } catch (RuntimeException e) {
        return ResponseEntity.notFound().build();
    }
    }



}
