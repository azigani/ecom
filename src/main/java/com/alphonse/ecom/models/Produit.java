package com.alphonse.ecom.models;

import com.alphonse.ecom.Dto.ProduitDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mysql.cj.protocol.ColumnDefinition;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@Table(name = "produit")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomProduit;

    private Long prixProduit;


    @Lob
    private String description;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] image;
    /**
     * Chargement légé au detriment de Eager qui charge toutes les données!
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categorie_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Categorie categorie;


    public ProduitDto getProductDto(){
        ProduitDto produitDto = new ProduitDto();
         produitDto.setId(id);
         produitDto.setDescription(description);
         produitDto.setNomProduit(nomProduit);
         produitDto.setPrixProduit(prixProduit);
         produitDto.setImageByte(image);
         produitDto.setCategorieId(categorie.getId());
         produitDto.setImageByte(image);
         return  produitDto;

    }

}
