package com.alphonse.ecom.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "categorie")
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomcategorie;

    @Lob
    private String description;




}
