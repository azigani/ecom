package com.alphonse.ecom.Dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProduitDto {


    private Long id;

    private String nomProduit;

    private Long prixProduit;



    private String description;


    private byte[] imageByte;

    private Long categorieId;

    private MultipartFile multipartFile;


}
