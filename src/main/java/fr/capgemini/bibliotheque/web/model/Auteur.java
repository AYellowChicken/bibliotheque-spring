package fr.capgemini.bibliotheque.web.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "auteur")
public class Auteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="NumAuteur")
    private int numAuteur;

    @Column(name="NomAu")
    private String nomAu;

    @Column(name="PrenomAu")
    private String prenomAu;
    
    @Column(name="NationaliteAu")
    private String nationaliteAu;

    @OneToMany(mappedBy = "auteur")
    private List<Livre> livres;

    public Auteur() {
    }

    public Auteur(int numAuteur, String nomAu, String prenomAu, String nationaliteAu, List<Livre> livres) {
        this.numAuteur = numAuteur;
        this.nomAu = nomAu;
        this.prenomAu = prenomAu;
        this.nationaliteAu = nationaliteAu;
        this.livres = livres;
    }

    public int getNumAuteur() {
        return this.numAuteur;
    }

    public void setNumAuteur(int numAuteur) {
        this.numAuteur = numAuteur;
    }

    public String getNomAu() {
        return this.nomAu;
    }

    public void setNomAu(String nomAu) {
        this.nomAu = nomAu;
    }

    public String getPrenomAu() {
        return this.prenomAu;
    }

    public void setPrenomAu(String prenomAu) {
        this.prenomAu = prenomAu;
    }

    public String getNationaliteAu() {
        return this.nationaliteAu;
    }

    public void setNationaliteAu(String nationaliteAu) {
        this.nationaliteAu = nationaliteAu;
    }

    public List<Livre> getLivres() {
        return this.livres;
    }

    public void setLivres(List<Livre> livres) {
        this.livres = livres;
    }

    @Override
    public String toString() {
        return "{" +
            " numAuteur='" + getNumAuteur() + "'" +
            ", nomAu='" + getNomAu() + "'" +
            ", prenomAu='" + getPrenomAu() + "'" +
            ", nationaliteAu='" + getNationaliteAu() + "'" +
            ", livres='" + getLivres() + "'" +
            "}";
    }
    
}
