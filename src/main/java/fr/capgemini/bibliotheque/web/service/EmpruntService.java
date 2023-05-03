package fr.capgemini.bibliotheque.web.service;

import fr.capgemini.bibliotheque.web.model.Emprunt;
import java.util.List;

public interface EmpruntService {
    List<Emprunt> findAll();
    List<Emprunt> findByNumAbonne(int numAbonne);
    // Abonne updateAbonne(Abonne abonne) throws NullPointerException;
    // void deleteAbonne(int NumAbonne) throws NullPointerException;
    // Abonne save(Abonne abonne);
    // void delete(int numAbonne);
}
