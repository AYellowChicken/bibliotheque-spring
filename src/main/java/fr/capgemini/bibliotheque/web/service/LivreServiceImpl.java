package fr.capgemini.bibliotheque.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.capgemini.bibliotheque.web.model.Livre;
import fr.capgemini.bibliotheque.web.repository.LivreRepository;


@Service
@Qualifier("LivreServiceJPAImpl")
public class LivreServiceImpl implements LivreService {
    
    @Autowired
    private LivreRepository livreRepository;
    private final Logger logger = LoggerFactory.getLogger(EmpruntServiceImpl.class);

    public LivreServiceImpl() {
        logger.info("LivreServiceImpl created with empty constructor!");
    }

    @Override
    public List<Livre> findAll() {
        return livreRepository.findAllByOrderByIsbnLivre();
    }

    @Override
    public Optional<Livre> findByISBN(int isbn) {
        return Optional.ofNullable(livreRepository.findByIsbnLivre(isbn));
    }

    @Override
    public List<Livre> findByAuteur(String firstName, String lastName) {
        return livreRepository.findByAuteurQuery(firstName, lastName);
    }

    @Override
    public List<Livre> findByTitle(String title) {
        return livreRepository.findByTitreIgnoreCaseContaining(title);
    }

    @Override
    public Livre updateLivre(Livre livre) throws NullPointerException {
        Optional<Livre> livreToUpdateSearch = findByISBN(livre.getIsbnLivre());
        if (livreToUpdateSearch.isEmpty()) {
            throw new NullPointerException("Aucun livre à update trouvé");
        }
        Livre livreToUpdate = livreToUpdateSearch.get();
        livreToUpdate.setTitre(livre.getTitre());
        livreToUpdate.setAuteur(livre.getAuteur());
        livreToUpdate.setEditeur(livre.getEditeur());
        livreToUpdate.setNbrePages(livre.getNbrePages());
        save(livreToUpdate);

        return livreToUpdate;
    }

    @Override
    public void deleteLivre(int isbnLivre) throws NullPointerException {
        Optional<Livre> livreToDeleteSearch = findByISBN(isbnLivre);
        if (livreToDeleteSearch.isEmpty()) {
            throw new NullPointerException("Aucun livre à delete trouvé");
        }
        delete(isbnLivre);
    }

    @Override
    public Livre save (Livre livre) {
        return livreRepository.save(livre);
    }

    @Override
    public void delete(int isbnLivre) {
        livreRepository.deleteById(isbnLivre);
    }
}
