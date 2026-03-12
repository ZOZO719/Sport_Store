package com.store.store.dtos.user;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String userName;
    private String email ; 
    private String password; 

}
