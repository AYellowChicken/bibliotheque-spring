package fr.capgemini.bibliotheque.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.capgemini.bibliotheque.web.model.Emprunt;
import fr.capgemini.bibliotheque.web.service.EmpruntService;

@RestController
@RequestMapping("/emprunts")
public class EmpruntController {

    private EmpruntService empruntService;

    public EmpruntController(EmpruntService empruntService) {
        this.empruntService = empruntService;
    }

    // Find all
    @GetMapping
    public ResponseEntity<List<Emprunt>> findAll() {
        return new ResponseEntity<List<Emprunt>>(empruntService.findAll(), HttpStatus.OK);
    }    

    // Find by NumAbonne
    @GetMapping("/{numAbonne}")
    public ResponseEntity<?> findById(@PathVariable int numAbonne) {
        return new ResponseEntity<List<Emprunt>>(empruntService.findByNumAbonne(numAbonne), HttpStatus.OK);
    }

}