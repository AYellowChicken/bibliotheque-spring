package fr.capgemini.bibliotheque.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.capgemini.bibliotheque.web.model.Auteur;
import fr.capgemini.bibliotheque.web.repository.AuteurRepository;


@Service
@Qualifier("AuteurServiceJPAImpl")
public class AuteurServiceImpl implements AuteurService {
    
    @Autowired
    private AuteurRepository auteurRepository;
    private final Logger logger = LoggerFactory.getLogger(EmpruntServiceImpl.class);

    public AuteurServiceImpl() {
        logger.info("AuteurServiceImpl created with empty constructor!");
    }

    @Override
    public List<Auteur> findAll() {
        return auteurRepository.findAllByOrderByNumAuteur();
    }

    @Override
    public Optional<Auteur> findByNumAuteur(int numAuteur) {
        return Optional.ofNullable(auteurRepository.findByNumAuteur(numAuteur));
    }

    @Override
    public List<Auteur> findByName(String firstName, String lastName) {
        return auteurRepository.findByPrenomAuIgnoreCaseContainingOrNomAuIgnoreCaseContaining(firstName, lastName);
    }

    @Override
    public Auteur updateAuteur(Auteur auteur) throws NullPointerException {
        Optional<Auteur> auteurToUpdateSearch = findByNumAuteur(auteur.getNumAuteur());
        if (auteurToUpdateSearch.isEmpty()) {
            throw new NullPointerException("Aucun auteur à update trouvé");
        }
        Auteur auteurToUpdate = auteurToUpdateSearch.get();
        auteurToUpdate.setNomAu(auteur.getNomAu());
        auteurToUpdate.setPrenomAu(auteur.getPrenomAu());
        auteurToUpdate.setNationaliteAu(auteur.getNationaliteAu());
        save(auteurToUpdate);

        return auteurToUpdate;
    }

    @Override
    public void deleteAuteur(int numAuteur) throws NullPointerException {
        Optional<Auteur> auteurToDeleteSearch = findByNumAuteur(numAuteur);
        if (auteurToDeleteSearch.isEmpty()) {
            throw new NullPointerException("Aucun auteur à delete trouvé");
        }
        delete(numAuteur);
    }

    @Override
    public Auteur save (Auteur auteur) {
        return auteurRepository.save(auteur);
    }

    @Override
    public void delete(int numAuteur) {
        auteurRepository.deleteById(numAuteur);
    }
}
