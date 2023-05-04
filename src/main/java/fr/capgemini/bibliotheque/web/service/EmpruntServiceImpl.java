package fr.capgemini.bibliotheque.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.capgemini.bibliotheque.web.model.Emprunt;
import fr.capgemini.bibliotheque.web.repository.EmpruntRepository;


@Service
@Qualifier("EmpruntServiceJPAImpl")
public class EmpruntServiceImpl implements EmpruntService {
    
    @Autowired
    private EmpruntRepository empruntRepository;
    private final Logger logger = LoggerFactory.getLogger(EmpruntServiceImpl.class);

    public EmpruntServiceImpl() {
        logger.info("EmpruntServiceImpl created with empty constructor!");
    }

    @Override
    public List<Emprunt> findAll() {
        return empruntRepository.findAll();
    }

    @Override
    public List<Emprunt> findByNumAbonne(int numAbonne) {
        return empruntRepository.findByNumAbonne(numAbonne);
    }

    @Override
    public List<Emprunt> findByNumAbonneAndIsbnLivre(int numAbonne, int isbnLivre) {
        return empruntRepository.findByNumAbonneAndIsbnLivre(numAbonne, isbnLivre);
    }

    @Override
    public List<Emprunt> findByIsbnLivre(int isbnLivre) {
        return empruntRepository.findByIsbnLivre(isbnLivre);
    }

/**
 * This Java function checks if an abonne (library member) can borrow a book by verifying if they have
 * any non-returned books.
 * 
 * @param numAbonne The parameter numAbonne is an integer representing the unique identifier of a
 * subscriber in a library system.
 * @return A boolean value indicating whether an emprunt (loan) is possible for the given abonne
 * (subscriber) based on whether they have any non-rendu (not returned) emprunts.
 */
    @Override
    public boolean isEmpruntPossibleForAbonne(int numAbonne) {
        List<Emprunt> empruntsAbonne = findByNumAbonne(numAbonne);
        return empruntsAbonne.stream().noneMatch(Emprunt::isNonRendu); // Using streams is a nice way to keep it readable
    }

/**
 * This Java function checks if it is possible to borrow a book with a given ISBN by verifying if there
 * are no non-returned loans for that book.
 * 
 * @param isbnLivre isbnLivre
 * @return The method is returning a boolean value that indicates whether it is possible to borrow a
 * book with the given ISBN number or not. It checks if there are any unreturned loans for the book
 * with the given ISBN number and returns true if there are none, and false otherwise.
 */
    @Override
    public boolean isEmpruntPossibleForIsbn(int isbnLivre) {
        List<Emprunt> empruntsIsbn = findByIsbnLivre(isbnLivre);
        return empruntsIsbn.stream().noneMatch(Emprunt::isNonRendu);
    }

    @Override
    public Emprunt save(Emprunt emprunt) {
        return empruntRepository.save(emprunt);
    }

}
