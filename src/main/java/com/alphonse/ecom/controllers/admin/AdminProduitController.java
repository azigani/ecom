package com.alphonse.ecom.controllers.admin;

import com.alphonse.ecom.Dto.ProduitDto;
import com.alphonse.ecom.models.Produit;
import com.alphonse.ecom.services.admin.categorie.produit.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminProduitController {

    private final ProduitService produitService;

    @PostMapping("/ajouterProduit")
    public ResponseEntity<ProduitDto> ajouterProduit(@ModelAttribute ProduitDto produitDto) {
        ProduitDto produitDto1 = produitService.enregistrerProduit(produitDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produitDto1);

    }

@GetMapping("/listeProduits")
    public ResponseEntity<List<ProduitDto>> listeProduits() {
        List<ProduitDto> produitDtos = produitService.listeProduits();
        return  ResponseEntity.ok(produitDtos);

    }


}

