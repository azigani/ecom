package com.alphonse.ecom.controllers.admin;

import com.alphonse.ecom.Dto.CategorieDto;
import com.alphonse.ecom.models.Categorie;
import com.alphonse.ecom.services.admin.categorie.CategorieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AdminCategorieController {

    private final CategorieService categorieService;

//    @PostMapping("/enregistrer-categorie")

    @PostMapping("/enregistrerCategorie")
    public ResponseEntity<Categorie> enregistrerCategorieAdmin(@RequestBody CategorieDto categorieDto) {

        System.out.println("categrieDto"+categorieDto);
        Categorie categorie = categorieService.enregistrerCategorie(categorieDto);
        System.out.println("categorie"+categorie);
        System.out.println("categorieDto"+categorieDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(categorie);


    }

    @GetMapping("/listeCatgeorie")
    public ResponseEntity<List<Categorie>> listeCategories() {
        return  ResponseEntity.ok(categorieService.listeCatgeorie());
    }
}

