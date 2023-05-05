package fr.capgemini.bibliotheque.web.restcontroller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.dao.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.capgemini.bibliotheque.web.model.Abonne;
import fr.capgemini.bibliotheque.web.service.AbonneService;

@RestController
@RequestMapping("/abonnes")
public class AbonneController {

    private final Logger logger = LoggerFactory.getLogger(AbonneController.class);
    private AbonneService abonneService;

    public AbonneController(AbonneService abonneService) {
        this.abonneService = abonneService;
    }

    // Find all
    @GetMapping
    public ResponseEntity<List<Abonne>> findAll() {
        return new ResponseEntity<List<Abonne>>(abonneService.findAll(), HttpStatus.OK);
    }

    // Find by prenom and nom
    @GetMapping("/searchName")
    public ResponseEntity<?> findByNames(
            @RequestParam(required = false) String prenomAb,
            @RequestParam(required = false) String nomAb) {
        Optional<Abonne> result = abonneService.findByFirstNameAndLastName(prenomAb, nomAb);
        if (!result.isEmpty()) {
            return new ResponseEntity<Abonne>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Find by NumAbonne
    @GetMapping("/{numAbonne}")
    public ResponseEntity<?> findById(@PathVariable int numAbonne) {
        Optional<Abonne> result = abonneService.findByNumAbonne(numAbonne);
        if (!result.isEmpty()) {
            return new ResponseEntity<Abonne>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Create Abonne (Json body)
    @PostMapping
    public ResponseEntity<Abonne> createAbonne(@RequestBody Abonne abonne) {
        logger.info("Creating abonné");
        try {
            Abonne insertedAbonne = abonneService.save(abonne);
            return new ResponseEntity<Abonne>(insertedAbonne, HttpStatus.CREATED);
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update Abonne (Json body)
    @PutMapping
    public ResponseEntity<Abonne> updateAbonne(@RequestBody Abonne abonne) {
        logger.info("Updating abonné " + abonne.getNumAbonne());
        try {
            Abonne abonneToUpdate = abonneService.updateAbonne(abonne);
            return new ResponseEntity<Abonne>(abonneToUpdate, HttpStatus.OK); // We can also return just "updated"
                                                                              // status code with the id and you can ask
                                                                              // for it later
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    // Delete Abonne (numAbonne)
    @DeleteMapping("/{numAbonne}")
    public ResponseEntity<Void> deleteAbonne(@PathVariable int numAbonne) {
        logger.info("Deleting abonné " + numAbonne);
        try {
            abonneService.deleteAbonne(numAbonne);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    // UNUSED : Find by all params
    @GetMapping("/search")
    public ResponseEntity<?> findByAllParams(@RequestParam(required = false) Integer numAbonne,
            @RequestParam(required = false) String prenomAb,
            @RequestParam(required = false) String nomAb,
            @RequestParam(required = false) String addresseAb,
            @RequestParam(required = false) String telephoneAb) {
        Optional<Abonne> result = abonneService.findByAllParams(numAbonne, prenomAb, nomAb, addresseAb, telephoneAb);
        if (!result.isEmpty()) {
            return new ResponseEntity<Abonne>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}