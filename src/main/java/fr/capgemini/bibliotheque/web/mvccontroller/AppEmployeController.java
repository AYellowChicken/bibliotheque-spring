package fr.capgemini.bibliotheque.web.mvccontroller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppEmployeController {

    public AppEmployeController() {
    }
    
    @GetMapping({"/app/employe", "/app/employe/"})
    public String employeHome(Model model) {
        return "employe-home";
    }

    @GetMapping({"/app/employe/abonnes", "/app/employe/abonnes/"})
    public String employeAbonnes(Model model) {
        return "employe-abonnes";
    }

    @GetMapping({"/app/employe/livres", "/app/employe/livres/"})
    public String employeLivres(Model model) {
        return "employe-livres";
    }

    @GetMapping({"/app/employe/auteurs", "/app/employe/auteurs"})
    public String employeAuteur(Model model) {
        return "employe-auteurs";
    }
    
    @GetMapping({"/app/employe/emprunts", "/app/employe/emprunts/"})
    public String employeEmprunts(Model model) {
        return "employe-emprunts";
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