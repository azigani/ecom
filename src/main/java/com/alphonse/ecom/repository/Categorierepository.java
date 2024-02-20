package com.alphonse.ecom.repository;

import com.alphonse.ecom.models.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Categorierepository extends JpaRepository<Categorie, Long> {


}
