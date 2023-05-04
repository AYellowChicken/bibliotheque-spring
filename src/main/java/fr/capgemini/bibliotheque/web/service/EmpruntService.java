package fr.capgemini.bibliotheque.web.service;

import fr.capgemini.bibliotheque.web.model.Emprunt;
import java.util.List;

public interface EmpruntService {
    List<Emprunt> findAll();
    List<Emprunt> findByNumAbonne(int numAbonne);
    List<Emprunt> findByNumAbonneAndIsbnLivre(int numAbonne, int isbnLivre);
    List<Emprunt> findByIsbnLivre(int isbnLivre);
    boolean isEmpruntPossibleForAbonne(int numAbonne);
    boolean isEmpruntPossibleForIsbn(int isbnLivre);
    Emprunt save(Emprunt emprunt);
}
