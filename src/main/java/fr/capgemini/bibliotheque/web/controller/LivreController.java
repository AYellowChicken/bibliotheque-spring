package fr.capgemini.bibliotheque.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.capgemini.bibliotheque.web.model.Livre;
import fr.capgemini.bibliotheque.web.service.LivreService;

@RestController
@RequestMapping("/livres")
public class LivreController {

    private LivreService livreService;

    public LivreController(LivreService livreService) {
        this.livreService = livreService;
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

    // Find by Auteur
    @GetMapping("/searchAuteur")
    public ResponseEntity<List<Livre>> findByAuteur(@RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        return new ResponseEntity<List<Livre>>(livreService.findByAuteur(firstName, lastName), HttpStatus.OK);
    }

    // Find by Title
    @GetMapping("/searchTitle")
    public ResponseEntity<List<Livre>> findByTitle(@RequestParam(required = true) String title) {
        return new ResponseEntity<List<Livre>>(livreService.findByTitle(title), HttpStatus.OK);
    }

}