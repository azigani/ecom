package com.alphonse.ecom.Dto;

import lombok.Data;

@Data
public class SignInrequest {
    private String email;
    private String password;
    private String nom;
}
