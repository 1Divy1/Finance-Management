package com.david.Finance_Management.model.response.auth;

import com.david.Finance_Management.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RegisterResponse {
    private UserDTO user;
}
