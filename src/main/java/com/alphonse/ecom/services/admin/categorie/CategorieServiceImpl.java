package com.alphonse.ecom.services.admin.categorie;

import com.alphonse.ecom.Dto.CategorieDto;
import com.alphonse.ecom.models.Categorie;
import com.alphonse.ecom.repository.Categorierepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategorieServiceImpl implements CategorieService {

    private final Categorierepository categorierepository;

    public Categorie enregistrerCategorie(CategorieDto categorieDto) {

        System.out.println("categorie"+categorieDto);

        Categorie categorie = new Categorie();
        categorie.setNomcategorie(categorieDto.getNomcategorie());
        categorie.setDescription(categorieDto.getDescription());

        System.out.println("categorie"+categorie);
        return  categorierepository.save(categorie);

    }

    public List<Categorie> listeCatgeorie(){
        return      categorierepository.findAll();
    }
}
