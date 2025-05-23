package com.david.Finance_Management.model.dto;

import com.david.Finance_Management.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private String first_name;
    private String last_name;
    private String email;

    static public UserDTO mapToDTO(UserEntity userEntity) {
        return new UserDTO(
                userEntity.getFirst_name(),
                userEntity.getLast_name(),
                userEntity.getEmail()
        );
    }
}
