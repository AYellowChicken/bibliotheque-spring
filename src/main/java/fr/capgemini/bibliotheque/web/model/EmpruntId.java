package fr.capgemini.bibliotheque.web.model;

import java.util.Date;

public class EmpruntId {

    private Date dateEmprunt;

    private Abonne abonne;

    private Livre livre;

    public Date getDateEmprunt() {
        return this.dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public Abonne getAbonne() {
        return this.abonne;
    }

    public void setAbonne(Abonne abonne) {
        this.abonne = abonne;
    }

    public Livre getLivre() {
        return this.livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

}