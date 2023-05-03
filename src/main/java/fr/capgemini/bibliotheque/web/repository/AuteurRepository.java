package fr.capgemini.bibliotheque.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.capgemini.bibliotheque.web.model.Auteur;

public interface AuteurRepository extends JpaRepository<Auteur, Integer> {
    
}
