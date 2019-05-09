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
@Table(name="StatistiquesGeneral")
@Data
public class StatistiquesGeneral implements Serializable {






	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Item_ID")
	String itemId;
	
	@Column(name="nClics_Item")		//nb de clics sur l'objet en general
	long nClicsItem;

	@Column(name="Mot")		//mot le + recherche en general
	String mot;
	
	@Column(name="nClics_Mot")		//nb de recherches sur ce mot en general
	long nClicsMot;
	
	@Column(name="Categorie")		//catégorie auquel l'objet appartient
	String categorie ;
	
	@Column(name="nClics_Categorie")		//nb de clics dans cette catégorie en general
	long nClicsCategorie;
	
	
	public StatistiquesGeneral() {}
	
	
	public StatistiquesGeneral(String itemId, long n, String mot, long p, String categorie, long q) {
		this.itemId = itemId;
		nClicsItem = n ;
		this.mot = mot ;
		nClicsMot = p ;
		this.categorie = categorie ;
		nClicsCategorie = q ;
	}
	
	public StatistiquesGeneral(long n, String mot, long p, String categorie, long q) {
		nClicsItem = n ;
		this.mot = mot ;
		nClicsMot = p ;
		this.categorie = categorie ;
		nClicsCategorie = q ;
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
		return "Statistiques [vues de l'item "+itemId+ " = " + nClicsItem + ", vues de la categorie associee " + categorie + " = " + nClicsCategorie + ", mot general le + courant = " + mot + " avec " + nClicsMot + " vues]";
	}

	


}
