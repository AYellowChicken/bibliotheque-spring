package fr.capgemini.bibliotheque.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import fr.capgemini.bibliotheque.web.model.Auteur;

public interface AuteurRepository extends JpaRepository<Auteur, Integer> {
    List<Auteur> findAllByOrderByNumAuteur();
    Auteur findByNumAuteur(int NumAuteur);
    List<Auteur> findByPrenomAuIgnoreCaseContainingOrNomAuIgnoreCaseContaining(@Param("prenomAu") String prenomAu, @Param("nomAu") String nomAu);
}