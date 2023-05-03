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









// package fr.capgemini.bibliotheque.web.model;

// import java.io.Serializable;
// import java.util.Date;

// import jakarta.persistence.Column;
// import jakarta.persistence.Embeddable;
// import jakarta.persistence.Temporal;
// import jakarta.persistence.TemporalType;

// @Embeddable
// public class EmpruntId implements Serializable {
//     @Column(name = "NumAbonne")
//     private Long numAbonne;

//     @Column(name = "ISBNLivre")
//     private Long isbnLivre;

//     @Column(name = "dateEmprunt")
//     @Temporal(TemporalType.DATE)
//     private Date dateEmprunt;

//     public EmpruntId() {
//     }

//     public EmpruntId(Long numAbonne, Long isbnLivre, Date dateEmprunt) {
//         this.numAbonne = numAbonne;
//         this.isbnLivre = isbnLivre;
//         this.dateEmprunt = dateEmprunt;
//     }

//     public Long getNumAbonne() {
//         return this.numAbonne;
//     }

//     public void setNumAbonne(Long numAbonne) {
//         this.numAbonne = numAbonne;
//     }

//     public Long getIsbnLivre() {
//         return this.isbnLivre;
//     }

//     public void setIsbnLivre(Long isbnLivre) {
//         this.isbnLivre = isbnLivre;
//     }

//     public Date getDateEmprunt() {
//         return this.dateEmprunt;
//     }

//     public void setDateEmprunt(Date dateEmprunt) {
//         this.dateEmprunt = dateEmprunt;
//     }

// }