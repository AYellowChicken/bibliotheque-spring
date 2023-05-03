package fr.capgemini.bibliotheque.web.model;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity
@IdClass(EmpruntId.class)
@Table(name = "emprunt")
public class Emprunt {

	@Id
    @Temporal(TemporalType.DATE)
	@Column(name="DateEmprunt")
	private Date dateEmprunt;

	@Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "isbnLivre", referencedColumnName = "ISBNLivre")
    private Livre livre;
	
	@Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "numAbonne", referencedColumnName = "NumAbonne")
    private Abonne abonne;

    @Temporal(TemporalType.DATE)
	@Column(name="DateRetourPrevue")
	private Date dateRetourPrevue;

    @Temporal(TemporalType.DATE)
	@Column(name="DateRetourEffective")
	private Date dateRetourEffective;

    @Transient
    private boolean retardNY;

    public boolean isRetardNY() {
        Date today = new Date();
        return (dateRetourEffective != null && dateRetourEffective.before(dateRetourPrevue))
				|| dateRetourEffective == null && dateRetourPrevue.after(today);
    }


	public Emprunt() {
	}

	public Emprunt(Abonne abonne, Livre livre, Date dateEmprunt, Date dateRetourPrevue, Date dateRetourEffective) {
		this.abonne = abonne;
		this.livre = livre;
		this.dateEmprunt = dateEmprunt;
		this.dateRetourPrevue = dateRetourPrevue;
		this.dateRetourEffective = dateRetourEffective;
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

	public Date getDateEmprunt() {
		return this.dateEmprunt;
	}

	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public Date getDateRetourPrevue() {
		return this.dateRetourPrevue;
	}

	public void setDateRetourPrevue(Date dateRetourPrevue) {
		this.dateRetourPrevue = dateRetourPrevue;
	}

	public Date getDateRetourEffective() {
		return this.dateRetourEffective;
	}

	public void setDateRetourEffective(Date dateRetourEffective) {
		this.dateRetourEffective = dateRetourEffective;
	}

	@Override
	public String toString() {
		return "{" +
			" abonne='" + getAbonne() + "'" +
			", livre='" + getLivre() + "'" +
			", dateEmprunt='" + getDateEmprunt() + "'" +
			", dateRetourPrevue='" + getDateRetourPrevue() + "'" +
			", dateRetourEffective='" + getDateRetourEffective() + "'" +
			"}";
	}

}
