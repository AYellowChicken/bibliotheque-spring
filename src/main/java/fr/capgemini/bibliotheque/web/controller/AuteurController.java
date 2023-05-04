package fr.capgemini.bibliotheque.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.capgemini.bibliotheque.web.model.Auteur;
import fr.capgemini.bibliotheque.web.service.AuteurService;

@RestController
@RequestMapping("/auteurs")
public class AuteurController {

    private AuteurService auteurService;
    private final Logger logger = LoggerFactory.getLogger(AuteurController.class);

    public AuteurController(AuteurService auteurService) {
        this.auteurService = auteurService;
    }

    // Find all
    @GetMapping
    public ResponseEntity<List<Auteur>> findAll() {
        return new ResponseEntity<List<Auteur>>(auteurService.findAll(), HttpStatus.OK);
    }    

    // Find by ISBN
    @GetMapping("/{numAuteur}")
    public ResponseEntity<?> findByNumAuteur(@PathVariable int numAuteur) {
        Optional<Auteur> result = auteurService.findByNumAuteur(numAuteur);
        if (!result.isEmpty()) {
            return new ResponseEntity<Auteur>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Find by Name
    @GetMapping("/searchName")
    public ResponseEntity<List<Auteur>> findByName(@RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        return new ResponseEntity<List<Auteur>>(auteurService.findByName(firstName, lastName), HttpStatus.OK);
    }

    // Create Auteur (Json body)
    @PostMapping
    public ResponseEntity<Auteur> createAuteur(@RequestBody Auteur auteur) {
        logger.info("Creating auteur");
        try {
            Auteur insertedAuteur = auteurService.save(auteur);
            return new ResponseEntity<Auteur>(insertedAuteur, HttpStatus.CREATED);
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Auteur> updateAuteur(@RequestBody Auteur auteur) {
        logger.info("Updating auteur " + auteur.getNumAuteur());
        try {
            Auteur auteurToUpdate = auteurService.updateAuteur(auteur);
            return new ResponseEntity<Auteur>(auteurToUpdate, HttpStatus.OK); // We can also return just "updated"
                                                                              // status code with the id and you can ask
                                                                              // for it later
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{numAuteur}")
    public ResponseEntity<Void> deleteLivre(@PathVariable int numAuteur) {
        logger.info("Deleting auteur " + numAuteur);
        try {
            auteurService.deleteAuteur(numAuteur);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}