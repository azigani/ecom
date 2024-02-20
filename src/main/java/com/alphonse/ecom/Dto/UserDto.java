package com.alphonse.ecom.Dto;

import com.alphonse.ecom.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long id;

    private String email;

    private String nom;

    private UserRole userRole;

}
