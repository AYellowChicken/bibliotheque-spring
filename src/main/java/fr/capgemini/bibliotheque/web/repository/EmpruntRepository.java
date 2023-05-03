package fr.capgemini.bibliotheque.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.capgemini.bibliotheque.web.model.Emprunt;
import fr.capgemini.bibliotheque.web.model.EmpruntId;

public interface EmpruntRepository extends JpaRepository<Emprunt, EmpruntId> {
    @Query("SELECT e FROM Emprunt e WHERE e.id.abonne.numAbonne = :numAbonne")
    List<Emprunt> findByNumAbonne(@Param("numAbonne") int numAbonne);
}
