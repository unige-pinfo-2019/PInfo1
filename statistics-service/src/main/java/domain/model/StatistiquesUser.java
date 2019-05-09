package main.java.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="StatistiquesUser")
@Data
public class StatistiquesUser implements Serializable {






	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="User_ID")
	String usrId;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Item_ID")
	String itemId;
	
	@Column(name="nClics_Item")
	long nClicsItem;		//nb de clics sur l'objet par cet utilisateur

	@Column(name="Mot")		//mot le + recherche par cet utilisateur
	String mot;
	
	@Column(name="nClics_Mot")		//nb de recherches sur ce mot par cet utilisateur
	long nClicsMot;
	
	@Column(name="Categorie")		//categorie auquel l'objet appartient
	String categorie ;
	
	@Column(name="nClics_Categorie")		//nb de clics sur un objet de cette categorie par cet utilisateur
	long nClicsCategorie;
	
	
	public StatistiquesUser() {}
	
	
	public StatistiquesUser(String usrId, String itemId, long n, String mot, long p, String categorie, long q) {
		this.usrId = usrId ;
		this.itemId = itemId;
		nClicsItem = n ;
		this.mot = mot ;
		nClicsMot = p ;
		this.categorie = categorie ;
		nClicsCategorie = q ;
	}
	
	public StatistiquesUser(long n, String mot, long p, String categorie, long q) {
		nClicsItem = n ;
		this.mot = mot ;
		nClicsMot = p ;
		this.categorie = categorie ;
		nClicsCategorie = q ;
	}
	

	public String getUsrId() {
		return usrId;
	}


	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}


	public String getItemId() {
		return itemId;
	}


	public void setItemId(String itemId) {
		this.itemId = itemId;
	}


	public long getnClicsItem() {
		return nClicsItem;
	}


	public void setnClicsItem(long nClicsItem) {
		this.nClicsItem = nClicsItem;
	}


	public String getMot() {
		return mot;
	}


	public void setMot(String mot) {
		this.mot = mot;
	}


	public long getnClicsMot() {
		return nClicsMot;
	}


	public void setnClicsMot(long nClicsMot) {
		this.nClicsMot = nClicsMot;
	}


	public String getCategorie() {
		return categorie;
	}


	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}


	public long getnClicsCategorie() {
		return nClicsCategorie;
	}


	public void setnClicsCategorie(long nClicsCategorie) {
		this.nClicsCategorie = nClicsCategorie;
	}


	@Override
	public String toString() {
		return "Statistiques pour l'utilisateur " + usrId + " [vues de l'item "+itemId+ " = " + nClicsItem + ", vues de la catéorie associée " + categorie + " = " + nClicsCategorie + ", mot général le + courant = " + mot + ", avec " + nClicsCategorie + " vues]";
	}

	


}