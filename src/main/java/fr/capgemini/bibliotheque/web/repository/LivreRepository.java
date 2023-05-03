package fr.capgemini.bibliotheque.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.capgemini.bibliotheque.web.model.Auteur;
import fr.capgemini.bibliotheque.web.model.Livre;

public interface LivreRepository extends JpaRepository<Livre, Integer> {
    List<Livre> findAllByOrderByIsbnLivre();
    Livre findByIsbnLivre(int isbnLivre);
    List<Livre> findByTitre(String titre);
    List<Livre> findByAuteur(Auteur auteur);

    // "SELECT b FROM Books b JOIN b.author a WHERE a.firstName = :authorFirstName OR a.lastName = :authorLastName"
    // "(:numAbonne IS NULL OR a.numAbonne = :numAbonne) AND " +
    // "(:prenomAb IS NULL OR LOWER(a.prenomAb) LIKE LOWER(CONCAT('%', :prenomAb, '%'))) AND " +
    // "(:nomAb IS NULL OR LOWER(a.nomAb) LIKE LOWER(CONCAT('%', :nomAb, '%'))) AND " +
    // "(:addressAb IS NULL OR LOWER(a.addressAb) LIKE LOWER(CONCAT('%', :addressAb, '%'))) AND " +
    // "(:telephoneAb IS NULL OR a.telephoneAb = :telephoneAb)")
    
    // @Query(value="SELECT * FROM livre l INNER JOIN auteur a ON l.num_auteur = a.num_auteur WHERE a.prenom_au LIKE :firstName OR a.nom_au = :lastName", nativeQuery = true)
    // @Query("SELECT l FROM livre l JOIN l.auteur a WHERE a.PrenomAu = :firstName OR a.NomAu = :lastName")
    // List<Livre> findByAuteurQuery(String firstName, String lastName);

    @Query("SELECT l FROM Livre l JOIN l.auteur a WHERE LOWER(a.prenomAu) LIKE LOWER(CONCAT('%', :firstName, '%')) OR LOWER(a.nomAu) LIKE LOWER(CONCAT('%', :lastName, '%'))")
    List<Livre> findByAuteurQuery(@Param("firstName") String firstName, 
                                @Param("lastName") String lastName);
    

    List<Livre> findByTitreIgnoreCaseContaining(String titre);
}
