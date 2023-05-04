package fr.capgemini.bibliotheque.web.service;

import fr.capgemini.bibliotheque.web.model.Livre;
import java.util.List;
import java.util.Optional;

public interface LivreService {
    List<Livre> findAll();
    Optional<Livre> findByISBN(int isbn);
    List<Livre> findByAuteur(String firstName, String lastName);
    List<Livre> findByTitle(String title);
    Livre updateLivre(Livre livre) throws NullPointerException;
    void deleteLivre(int isbnLivre) throws NullPointerException;
    Livre save (Livre livre);
    void delete(int isbnLivre);
}
