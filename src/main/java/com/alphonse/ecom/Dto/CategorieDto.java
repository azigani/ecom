package com.alphonse.ecom.Dto;

import com.alphonse.ecom.enums.UserRole;
import lombok.Data;

import javax.persistence.Lob;

@Data
public class CategorieDto {

//    private Long id;

    private String nomcategorie;

//    @Lob
    private String description;


}
