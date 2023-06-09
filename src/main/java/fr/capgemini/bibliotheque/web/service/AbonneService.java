package fr.capgemini.bibliotheque.web.service;

import fr.capgemini.bibliotheque.web.model.Abonne;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AbonneService {
    Page<Abonne> findAll(Pageable pageable); // Unused but better to use pageing
    List<Abonne> findAll();
    Optional<Abonne> findByAllParams(Integer numAbonne, String prenomAb, String nomAb, String addressAb, String telephoneAb); // Unused
    Optional<Abonne> findByNumAbonne(int numAbonne);
    List<Abonne> findByFirstNameAndLastName(String prenomAb, String nomAb);
    Abonne updateAbonne(Abonne abonne) throws NullPointerException;
    void deleteAbonne(int NumAbonne) throws NullPointerException;
    Abonne save(Abonne abonne);
    void delete(int numAbonne);
}
