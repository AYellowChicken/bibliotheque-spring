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
        logger.info("EmpruntServiceImpl using JPA empty constructor!");
    }

    @Override
    public List<Emprunt> findAll() {
        return empruntRepository.findAll();
    }

    @Override
    public List<Emprunt> findByNumAbonne(int numAbonne) {
        return empruntRepository.findByNumAbonne(numAbonne);
    }
}
