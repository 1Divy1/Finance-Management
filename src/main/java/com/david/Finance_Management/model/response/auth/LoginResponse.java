package com.david.Finance_Management.model.response.auth;

import com.david.Finance_Management.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private UserDTO user;
    private String jwtToken;
}
