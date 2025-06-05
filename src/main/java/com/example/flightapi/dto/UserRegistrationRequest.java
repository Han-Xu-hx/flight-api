/**
 * User Registration Request DTO
 * Copyright (C) 2025
 * Created by hanxu on 2025/06/05.
 */
package com.example.flightapi.dto;

import lombok.Data;

/**
 * User Registration Request DTO
 */
@Data
public class UserRegistrationRequest {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String country;
    private String phone;
}
