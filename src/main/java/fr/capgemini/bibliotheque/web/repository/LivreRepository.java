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
    void deleteByIsbnLivre(int isbnLivre);

    @Query("SELECT l FROM Livre l JOIN l.auteur a WHERE LOWER(a.prenomAu) LIKE LOWER(CONCAT('%', :firstName, '%')) OR LOWER(a.nomAu) LIKE LOWER(CONCAT('%', :lastName, '%'))")
    List<Livre> findByAuteurQuery(@Param("firstName") String firstName, 
                                @Param("lastName") String lastName);
    

    List<Livre> findByTitreIgnoreCaseContaining(String titre);
}
