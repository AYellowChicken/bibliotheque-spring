package fr.capgemini.bibliotheque.web.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "livre")
public class Livre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ISBNLivre")
	private int isbnLivre;
	
    @Column(name="Titre")
    private String titre;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "NumAuteur", referencedColumnName = "NumAuteur")
    private Auteur auteur;
	
    @Column(name="Editeur")
    private String editeur;
    
    @Column(name="NbrePages")
    private int nbrePages;

    @OneToMany(mappedBy = "livre")
    @JsonIgnore
    private Set<Emprunt> emprunt;

    public Livre() {
    }

    public Livre(int isbnLivre, String titre, Auteur auteur, String editeur, int nbrePages, Set<Emprunt> emprunt) {
        this.isbnLivre = isbnLivre;
        this.titre = titre;
        this.auteur = auteur;
        this.editeur = editeur;
        this.nbrePages = nbrePages;
        this.emprunt = emprunt;
    }

    public int getIsbnLivre() {
        return this.isbnLivre;
    }

    public void setIsbnLivre(int isbnLivre) {
        this.isbnLivre = isbnLivre;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Auteur getAuteur() {
        return this.auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public String getEditeur() {
        return this.editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public int getNbrePages() {
        return this.nbrePages;
    }

    public void setNbrePages(int nbrePages) {
        this.nbrePages = nbrePages;
    }

    public Set<Emprunt> getEmprunt() {
        return this.emprunt;
    }

    public void setEmprunt(Set<Emprunt> emprunt) {
        this.emprunt = emprunt;
    }

    @Override
    public String toString() {
        return "{" +
            " isbnLivre='" + getIsbnLivre() + "'" +
            ", titre='" + getTitre() + "'" +
            ", auteur='" + getAuteur() + "'" +
            ", editeur='" + getEditeur() + "'" +
            ", nbrePages='" + getNbrePages() + "'" +
            ", emprunt='" + getEmprunt() + "'" +
            "}";
    }


}
