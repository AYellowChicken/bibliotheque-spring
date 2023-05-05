package fr.capgemini.bibliotheque.web.restcontroller;

import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.capgemini.bibliotheque.web.model.Emprunt;
import fr.capgemini.bibliotheque.web.model.Livre;
import fr.capgemini.bibliotheque.web.model.Abonne;
import fr.capgemini.bibliotheque.web.service.AbonneService;
import fr.capgemini.bibliotheque.web.service.EmpruntService;
import fr.capgemini.bibliotheque.web.service.LivreService;

@RestController
@RequestMapping("/emprunts")
public class EmpruntController {

    private EmpruntService empruntService;
    private LivreService livreService;
    private AbonneService abonneService;

    private final Logger logger = LoggerFactory.getLogger(EmpruntController.class);

    public EmpruntController(EmpruntService empruntService, LivreService livreService, AbonneService abonneService) {
        this.empruntService = empruntService;
        this.livreService = livreService;
        this.abonneService = abonneService;
    }

    // Find all
    @GetMapping
    public ResponseEntity<List<Emprunt>> findAll() {
        return new ResponseEntity<List<Emprunt>>(empruntService.findAll(), HttpStatus.OK);
    }    

    // Find by NumAbonne
    @GetMapping("/{numAbonne}")
    public ResponseEntity<?> findByNumAbonne(@PathVariable int numAbonne) {
        return new ResponseEntity<List<Emprunt>>(empruntService.findByNumAbonne(numAbonne), HttpStatus.OK);
    }        
    
    // Create an Emprunt
    @PostMapping
    public ResponseEntity<?> createEmprunt(@RequestParam int numAbonne, @RequestParam int isbnLivre) {
        logger.info("Creating emprunt for " + numAbonne + " and ISBN " + isbnLivre + " ?");
        
        try {
            Livre livreToInsert = livreService.findByISBN(isbnLivre)
                                    .orElseThrow(() -> new NullPointerException("Didn't find livre to borrow with ISBN : " + isbnLivre));
            Abonne abonneToInsert = abonneService.findByNumAbonne(numAbonne)
                                    .orElseThrow(() -> new NullPointerException("Didn't find abonne to borrow with numAbonne : " + numAbonne));    
            
            if (empruntService.isEmpruntPossibleForAbonne(numAbonne) && empruntService.isEmpruntPossibleForIsbn(isbnLivre)) { // TODO : refactor this for different error messages
                Date today = new Date();
                Date returnDate = Emprunt.generateReturnDate(today); // Will be one month after today with current implementation
                Emprunt empruntToInsert = new Emprunt(abonneToInsert, livreToInsert, today, returnDate, null);
                
                Emprunt insertedEmprunt = empruntService.save(empruntToInsert);
                logger.info("Emprunt created");
                return new ResponseEntity<Emprunt>(insertedEmprunt, HttpStatus.CREATED);

            } else {
                logger.info("Abonne forbidden or book already borrowed");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NullPointerException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Return an Emprunt
    @PutMapping
    public ResponseEntity<?> returnEmprunt(@RequestParam int numAbonne, @RequestParam int isbnLivre) {
        logger.info("Returning emprunt for " + numAbonne + " and ISBN " + isbnLivre + " ?");
        try {
            List<Emprunt> empruntsToReturn = empruntService.findByNumAbonneAndIsbnLivre(numAbonne, isbnLivre);
            if (empruntsToReturn.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            
            // We can only return the latest Emprunt with this numAbonne and ISBN. Additional logic might be implemented later (for instance, whether there are multiple current Emprunt with this same ISBN/NumAbonne...)
            // But we just use latest Emprunt here, it's good enough.
            Emprunt latestEmprunt = Collections.max(empruntsToReturn, Comparator.comparing(Emprunt::getDateRetourPrevue));
            latestEmprunt.setDateRetourEffective(new Date());
            
            Emprunt insertedEmprunt = empruntService.save(latestEmprunt);
            logger.info("Returned emprunt");
            return new ResponseEntity<Emprunt>(insertedEmprunt, HttpStatus.OK);

        } catch (DataAccessException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}