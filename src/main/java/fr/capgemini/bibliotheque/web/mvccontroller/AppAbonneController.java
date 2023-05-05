package fr.capgemini.bibliotheque.web.mvccontroller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import fr.capgemini.bibliotheque.web.model.Auteur;
import fr.capgemini.bibliotheque.web.model.Emprunt;
import fr.capgemini.bibliotheque.web.service.AuteurService;
import fr.capgemini.bibliotheque.web.service.EmpruntService;

@Controller
public class AppAbonneController {

    private EmpruntService empruntService;
    private AuteurService auteurService;

    public AppAbonneController(EmpruntService empruntService, AuteurService auteurService) {
        this.empruntService = empruntService;
        this.auteurService = auteurService;
    }
    
    @GetMapping({"/app/abonne", "/app/abonne/", "/app/abonne/myemprunts"})
    public String abonneHome(Model model) {
        return "abonne-home";
    }
    
    @GetMapping({"/app/abonne/searchbook", "/app/abonne/searchbook/"})
    public String abonneSearchBook(Model model) {
        return "abonne-books";
    }


    // Old mappings using model and thymeleaf instead of Jquery+Ajax

    // @GetMapping("/app/abonne")
    // public String homeAbonne(Model model) {
    //     List<Auteur> auteurs = auteurService.findAll();
    //     model.addAttribute("auteurs", auteurs);

    //     List<Emprunt> emprunts = empruntService.findAll();
    //     model.addAttribute("emprunts", emprunts);
        
    //     return "abonne-index";
    // }    
    
    // @GetMapping("/app/abonne/myempruntsold")
    // public String abonneEmpruntsOld(Model model, 
    //                 @RequestParam(value="numAbonne", required = true) int numAbonne) {
    //     List<Emprunt> emprunts = empruntService.findByNumAbonne(numAbonne);
    //     model.addAttribute("emprunts", emprunts);
        
    //     return "abonne-emprunts";
    // }    

}