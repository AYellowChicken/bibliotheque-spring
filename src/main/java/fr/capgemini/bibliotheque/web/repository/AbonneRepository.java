package fr.capgemini.bibliotheque.web.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.capgemini.bibliotheque.web.model.Abonne;

@Qualifier("AbonneJPARepository")
public interface AbonneRepository extends JpaRepository<Abonne, Integer> { 
    List<Abonne> findAllByOrderByNumAbonne();
    Abonne findByNumAbonne(int numAbonne);
    List<Abonne> findByPrenomAb(String prenomAb);
    Abonne findByPrenomAbIgnoreCaseContainingAndNomAbIgnoreCaseContaining(String prenomAb, String nomAb);
    








    // Unused
    // JPQL
    @Query("SELECT a FROM Abonne a WHERE " +
    "(:numAbonne IS NULL OR a.numAbonne = :numAbonne) AND " +
    "(:prenomAb IS NULL OR LOWER(a.prenomAb) LIKE LOWER(:prenomAb)) AND " +
    "(:nomAb IS NULL OR LOWER(a.nomAb) LIKE LOWER(:nomAb)) AND " +
    "(:addresseAb IS NULL OR LOWER(a.addresseAb) LIKE LOWER(:addresseAb)) AND " +
    "(:telephoneAb IS NULL OR a.telephoneAb = :telephoneAb)")
    Abonne findByAllParamsNicely(
        @Param("numAbonne") Integer numAbonne,
        @Param("prenomAb") String prenomAb,
        @Param("nomAb") String nomAb,
        @Param("addresseAb") String addressAb,
        @Param("telephoneAb") String telephoneAb);
}