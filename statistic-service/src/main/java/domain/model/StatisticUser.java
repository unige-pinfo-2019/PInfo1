package main.java.domain.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="StatisticUser")
@Data
public class StatisticUser implements Serializable {






	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="User_ID")
	String userId;

	@Column(name="Livres")
	long nClicsLivres;

	@Column(name="Mobilite")
	long nClicsMobilite;

	@Column(name="Electronique")
	long nClicsElectronique;

	@Column(name="Notes")
	long nClicsNotes;

	@Column(name="Mobilier")
	long nClicsMobilier;


	public StatisticUser() {}


	public StatisticUser(String usrId, long n, long p, long q, long r, long s) {
			userId = usrId ;
			nClicsLivres = n ;
			nClicsMobilite = p ;
			nClicsElectronique = q ;
			nClicsNotes = r ;
			nClicsMobilier = s ;
	}

	public StatisticUser(long n, long p, long q, long r, long s) {
			userId = UUID.randomUUID().toString() ;
			nClicsLivres = n ;
			nClicsMobilite = p ;
			nClicsElectronique = q ;
			nClicsNotes = r ;
			nClicsMobilier = s ;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public long getnClicsLivres() {
		return nClicsLivres;
	}


	public void setnClicsLivres(long nClicsLivres) {
		this.nClicsLivres = nClicsLivres;
	}


	public long getnClicsMobilite() {
		return nClicsMobilite;
	}


	public void setnClicsMobilite(long nClicsMobilite) {
		this.nClicsMobilite = nClicsMobilite;
	}


	public long getnClicsElectronique() {
		return nClicsElectronique;
	}


	public void setnClicsElectronique(long nClicsElectronique) {
		this.nClicsElectronique = nClicsElectronique;
	}


	public long getnClicsNotes() {
		return nClicsNotes;
	}


	public void setnClicsNotes(long nClicsNotes) {
		this.nClicsNotes = nClicsNotes;
	}


	public long getnClicsMobilier() {
		return nClicsMobilier;
	}


	public void setnClicsMobilier(long nClicsMobilier) {
		this.nClicsMobilier = nClicsMobilier;
	}


	@Override
	public String toString() {
		return "Statistiques pour l'utilisateur " + userId + " [vues de la catégorie Livres = " + nClicsLivres + ", vues de la catégorie Mobilite = " + nClicsMobilite + ", vues de la catégorie Electronique = " + nClicsElectronique + ", vues de la catégorie Notes = " + nClicsNotes + ", vues de la catégorie Mobilier = " + nClicsMobilier + "]" ;
	}


}
