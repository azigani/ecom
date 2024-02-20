package com.alphonse.ecom.services.admin.categorie.produit;

import com.alphonse.ecom.Dto.CategorieDto;
import com.alphonse.ecom.Dto.ProduitDto;
import com.alphonse.ecom.models.Categorie;
import com.alphonse.ecom.models.Produit;

import java.util.List;

public interface ProduitService {

    ProduitDto enregistrerProduit(ProduitDto produitDto);

    List<ProduitDto> listeProduits();
}
