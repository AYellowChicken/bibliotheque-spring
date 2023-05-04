package fr.capgemini.bibliotheque.web.service;

import fr.capgemini.bibliotheque.web.model.Auteur;
import java.util.List;
import java.util.Optional;

public interface AuteurService {
    List<Auteur> findAll();
    Optional<Auteur> findByNumAuteur(int isbn);
    List<Auteur> findByName(String prenomAu, String nomAu);
    Auteur updateAuteur(Auteur auteur) throws NullPointerException;
    void deleteAuteur(int numAuteur) throws NullPointerException;
    Auteur save (Auteur auteur);
    void delete(int numAuteur);
}
