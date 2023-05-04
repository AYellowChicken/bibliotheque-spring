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
import fr.capgemini.bibliotheque.web.model.Livre;
import fr.capgemini.bibliotheque.web.service.AuteurService;
import fr.capgemini.bibliotheque.web.service.LivreService;

@RestController
@RequestMapping("/livres")
public class LivreController {

    private LivreService livreService;
    private AuteurService auteurService;
    private final Logger logger = LoggerFactory.getLogger(LivreController.class);

    public LivreController(LivreService livreService, AuteurService auteurService) {
        this.livreService = livreService;
        this.auteurService = auteurService;
    }

    // Find all
    @GetMapping
    public ResponseEntity<List<Livre>> findAll() {
        return new ResponseEntity<List<Livre>>(livreService.findAll(), HttpStatus.OK);
    }    

    // Find by ISBN
    @GetMapping("/{isbnLivre}")
    public ResponseEntity<?> findByISBN(@PathVariable int isbnLivre) {
        Optional<Livre> result = livreService.findByISBN(isbnLivre);
        if (!result.isEmpty()) {
            return new ResponseEntity<Livre>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Find by Auteur name
    @GetMapping("/searchAuteur")
    public ResponseEntity<List<Livre>> findByAuteurName(@RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        return new ResponseEntity<List<Livre>>(livreService.findByAuteur(firstName, lastName), HttpStatus.OK);
    }

    // Find by Title
    @GetMapping("/searchTitle")
    public ResponseEntity<List<Livre>> findByTitle(@RequestParam(required = true) String title) {
        return new ResponseEntity<List<Livre>>(livreService.findByTitle(title), HttpStatus.OK);
    }

    // Create Livre (Json body containing all params)
    @PostMapping
    public ResponseEntity<Livre> createLivre(@RequestBody Livre livre) {
        logger.info("Creating livre");
        try {
            Auteur auteurToInsertQuery = auteurService.findByNumAuteur(livre.getAuteur().getNumAuteur()).
                                            orElseThrow(() -> new NullPointerException("Auteur to add not found"));
            livre.setAuteur(auteurToInsertQuery);
            Livre insertedLivre = livreService.save(livre);
            return new ResponseEntity<Livre>(insertedLivre, HttpStatus.CREATED);
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Livre> updateLivre(@RequestBody Livre livre) {
        logger.info("Updating livre " + livre.getIsbnLivre());
        try {
            Auteur auteurToInsertQuery = auteurService.findByNumAuteur(livre.getAuteur().getNumAuteur()).
                                            orElseThrow(() -> new NullPointerException("Auteur to add not found"));
            livre.setAuteur(auteurToInsertQuery);
            Livre livreToUpdate = livreService.updateLivre(livre);
            return new ResponseEntity<Livre>(livreToUpdate, HttpStatus.OK); // We can also return just "updated"
                                                                              // status code with the id and you can ask
                                                                              // for it later
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{isbnLivre}")
    public ResponseEntity<Void> deleteLivre(@PathVariable int isbnLivre) {
        logger.info("Deleting livre " + isbnLivre);
        try {
            livreService.deleteLivre(isbnLivre);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}