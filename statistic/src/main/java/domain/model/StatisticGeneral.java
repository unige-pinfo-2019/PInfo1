package main.java.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="StatisticGeneral")
@Data
public class StatisticGeneral implements Serializable {






	public enum Categorie {Mobilite, Electronique, Cours, Livres}
	
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name="Item_ID")
	String itemId;
	
	@Column(name="nClics_Item")		//nb de clics sur l'objet en général
	long nClicsItem;

	@Column(name="Mot")		//mot le + recherché en général
	String mot;
	
	@Column(name="nClics_Mot")		//nb de recherches sur ce mot en général
	long nClicsMot;
	
	@Enumerated(EnumType.STRING)
	@Column(name="Categorie")		//catégorie auquel l'objet appartient
	Categorie categorie ;
	
	@Column(name="nClics_Categorie")		//nb de clics dans cette catégorie en général
	long nClicsCategorie;
	
	
	public StatisticGeneral() {}
	
	
	public StatisticGeneral(String itemId, long n, String mot, long p, Categorie categorie, long q) {
		this.itemId = itemId;
		nClicsItem = n ;
		this.mot = mot ;
		nClicsMot = p ;
		this.categorie = categorie ;
		nClicsCategorie = q ;
	}
	
	public StatisticGeneral(long n, String mot, long p, Categorie categorie, long q) {
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


	public Categorie getCategorie() {
		return categorie;
	}


	public void setCategorie(Categorie categorie) {
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
		return "Statistiques [vues de l'item "+itemId+ " = " + nClicsItem + ", vues de la catégorie associée " + categorie + " = " + nClicsCategorie + ", mot général recherché le + courant = " + mot + ", avec " + nClicsMot + " vues]";
	}

	


}