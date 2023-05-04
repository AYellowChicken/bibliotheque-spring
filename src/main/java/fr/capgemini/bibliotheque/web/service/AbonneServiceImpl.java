package fr.capgemini.bibliotheque.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.capgemini.bibliotheque.web.model.Abonne;
import fr.capgemini.bibliotheque.web.repository.AbonneRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@Qualifier("AbonneServiceJPAImpl")
public class AbonneServiceImpl implements AbonneService {
    
    @Autowired
    private AbonneRepository abonneRepository;
    private final Logger logger = LoggerFactory.getLogger(AbonneServiceImpl.class);

    public AbonneServiceImpl() {
        logger.info("AbonneServiceImpl created with empty constructor!");
    }

    @Override
    public List<Abonne> findAll() {
        return abonneRepository.findAllByOrderByNumAbonne();
    }

    // Unused
    @Override
    public Page<Abonne> findAll(Pageable pageable) {
        return abonneRepository.findAll(pageable);
    }

    // Unused
    @Override
    public Optional<Abonne> findByAllParams(Integer numAbonne, String prenomAb, String nomAb, String addresseAb, String telephoneAb) {
        return Optional.ofNullable(abonneRepository.findByAllParamsNicely(numAbonne, prenomAb, nomAb, addresseAb, telephoneAb));
    }

    @Override
    public Optional<Abonne> findByNumAbonne(int numAbonne) {
        return Optional.ofNullable(abonneRepository.findByNumAbonne(numAbonne));
    }

    @Override
    public Optional<Abonne> findByFirstNameAndLastName(String firstName, String lastName) {
        return Optional.ofNullable(abonneRepository.findByPrenomAbIgnoreCaseContainingAndNomAbIgnoreCaseContaining(firstName, lastName));
    }

    @Override
    public Abonne updateAbonne(Abonne abonne) {
        Abonne abonneToUpdate = findByNumAbonne(abonne.getNumAbonne())
                                        .orElseThrow(() -> new NullPointerException("Aucun abonné à update trouvé"));
                                        
        abonneToUpdate.setNomAb(abonne.getNomAb());
        abonneToUpdate.setPrenomAb(abonne.getPrenomAb());
        abonneToUpdate.setTelephoneAb(abonne.getTelephoneAb());
        abonneToUpdate.setAddresseAb(abonne.getAddresseAb());
        Abonne updatedAbonne = save(abonneToUpdate);
        return updatedAbonne;
    }

    @Override
    public void deleteAbonne(int numAbonne) {
        Optional<Abonne> abonneToDeleteSearch = findByNumAbonne(numAbonne);
        if (abonneToDeleteSearch.isEmpty()) {
            throw new NullPointerException("Aucun abonné à delete trouvé");
        }
        delete(numAbonne);
    }

    @Override
    public Abonne save(Abonne abonne) {
        return abonneRepository.save(abonne);
    }

    @Override
    public void delete(int id) {
        abonneRepository.deleteById(id);
    }
}
