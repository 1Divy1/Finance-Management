package com.david.Finance_Management.model.request.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String first_name;
    private String last_name;
    private String email;
    private String password;
}
