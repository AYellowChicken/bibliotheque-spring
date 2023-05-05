package fr.capgemini.bibliotheque.web.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "abonne")
public class Abonne {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="NumAbonne")
	private int numAbonne;

    @Column(name="NomAb")
	private String nomAb;

    @Column(name="PrenomAb")
	private String prenomAb;

    @Column(name="AddresseAb")
	private String addresseAb;

    @Column(name="TelephoneAb")
	private String telephoneAb;

    @OneToMany(mappedBy = "abonne", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Emprunt> emprunt;

    public Abonne() {
    }

    public Abonne(int numAbonne, String nomAb, String prenomAb, String addresseAb, String telephoneAb, Set<Emprunt> emprunt) {
        this.numAbonne = numAbonne;
        this.nomAb = nomAb;
        this.prenomAb = prenomAb;
        this.addresseAb = addresseAb;
        this.telephoneAb = telephoneAb;
        this.emprunt = emprunt;
    }

    public int getNumAbonne() {
        return this.numAbonne;
    }

    public void setNumAbonne(int numAbonne) {
        this.numAbonne = numAbonne;
    }

    public String getNomAb() {
        return this.nomAb;
    }

    public void setNomAb(String nomAb) {
        this.nomAb = nomAb;
    }

    public String getPrenomAb() {
        return this.prenomAb;
    }

    public void setPrenomAb(String prenomAb) {
        this.prenomAb = prenomAb;
    }

    public String getAddresseAb() {
        return this.addresseAb;
    }

    public void setAddresseAb(String addresseAb) {
        this.addresseAb = addresseAb;
    }

    public String getTelephoneAb() {
        return this.telephoneAb;
    }

    public void setTelephoneAb(String telephoneAb) {
        this.telephoneAb = telephoneAb;
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
            " numAbonne='" + getNumAbonne() + "'" +
            ", nomAb='" + getNomAb() + "'" +
            ", prenomAb='" + getPrenomAb() + "'" +
            ", addresseAb='" + getAddresseAb() + "'" +
            ", telephoneAb='" + getTelephoneAb() + "'" +
            ", emprunt='" + getEmprunt() + "'" +
            "}";
    }

}
