package com.alphonse.ecom.services.admin.categorie.produit;

import com.alphonse.ecom.Dto.CategorieDto;
import com.alphonse.ecom.Dto.ProduitDto;
import com.alphonse.ecom.models.Categorie;
import com.alphonse.ecom.models.Produit;
import com.alphonse.ecom.repository.Categorierepository;
import com.alphonse.ecom.repository.ProduitRepository;
import com.alphonse.ecom.services.admin.categorie.CategorieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;

    private final Categorierepository categorierepository;


    @Override
    public ProduitDto enregistrerProduit(ProduitDto produitDto) {

        Produit produit = new Produit();
        produit.setNomProduit(produitDto.getNomProduit());
        produit.setDescription(produitDto.getDescription());
        produit.setPrixProduit(produitDto.getPrixProduit());
        produit.setImage(produitDto.getImageByte());
        produit.setPrixProduit(produitDto.getPrixProduit());

//        Categorie categorie = categorierepository.findById(produitDto.getCategorieId());

        Categorie categorie = categorierepository.findById(produitDto.getCategorieId())
                .orElseThrow(() -> new IllegalArgumentException("Categorie not found"));

        produit.setCategorie(categorie);


        return produitRepository.save(produit).getProductDto();
        
    }

    @Override
    public List<ProduitDto> listeProduits() {
         List<Produit> produits = produitRepository.findAll();
         return  produits.stream().map(Produit::getProductDto).collect(Collectors.toList());
    }
}
