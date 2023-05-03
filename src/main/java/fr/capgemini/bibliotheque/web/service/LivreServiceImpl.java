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
        logger.info("Using JPA empty constructor!");
    }

    @Override
    public List<Livre> findAll() {
        return livreRepository.findAll();
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
}
