package fr.capgemini.bibliotheque.web.service;

import fr.capgemini.bibliotheque.web.model.Livre;
import java.util.List;
import java.util.Optional;

public interface LivreService {
    List<Livre> findAll();
    Optional<Livre> findByISBN(int isbn);
    List<Livre> findByAuteur(String firstName, String lastName);
    List<Livre> findByTitle(String title);

    // Abonne updateAbonne(Abonne abonne) throws NullPointerException;
    // void deleteAbonne(int NumAbonne) throws NullPointerException;
    // Abonne save(Abonne abonne);
    // void delete(int numAbonne);
}
