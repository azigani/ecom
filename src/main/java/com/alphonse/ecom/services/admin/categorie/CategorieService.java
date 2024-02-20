package com.alphonse.ecom.services.admin.categorie;

import com.alphonse.ecom.Dto.CategorieDto;
import com.alphonse.ecom.models.Categorie;

import java.util.List;

public interface CategorieService {

    Categorie enregistrerCategorie(CategorieDto categorieDto);

    public List<Categorie> listeCatgeorie();
}
