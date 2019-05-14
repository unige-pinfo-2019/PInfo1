package main.java.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import main.java.domain.model.StatisticGeneral.Categorie;


@Entity
@Table(name="StatisticUser")
@Data
public class StatisticUser implements Serializable {






	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="User_ID")
	String userId;
	
	@Id
	@Column(name="Item_ID")
	String itemId;
	
	@Column(name="nClics_Item")
	long nClicsItem;		//nb de clics sur l'objet par cet utilisateur

	@Column(name="Mot")		//mot le + recherché par cet utilisateur
	String mot;
	
	@Column(name="nClics_Mot")		//nb de recherches sur ce mot par cet utilisateur
	long nClicsMot;
	
	@Enumerated(EnumType.STRING)
	@Column(name="Categorie")		//catégorie auquel l'objet appartient
	Categorie categorie ;
	
	@Column(name="nClics_Categorie")		//nb de clics sur un objet de cette catégorie par cet utilisateur
	long nClicsCategorie;
	
	
	public StatisticUser() {}
	
	
	public StatisticUser(String usrId, String itemId, long n, String mot, long p, Categorie categorie, long q) {
		this.userId = usrId ;
		this.itemId = itemId;
		nClicsItem = n ;
		this.mot = mot ;
		nClicsMot = p ;
		this.categorie = categorie ;
		nClicsCategorie = q ;
	}
	
	public StatisticUser(long n, String mot, long p, Categorie categorie, long q) {
		nClicsItem = n ;
		this.mot = mot ;
		nClicsMot = p ;
		this.categorie = categorie ;
		nClicsCategorie = q ;
	}
	

	public String getUsrId() {
		return userId;
	}


	public void setUsrId(String usrId) {
		this.userId = usrId;
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
		return "Statistiques pour l'utilisateur " + userId + " [vues de l'item "+itemId+ " = " + nClicsItem + ", vues de la catégorie associée " + categorie + " = " + nClicsCategorie + ", mot recherché le + courant = " + mot + ", avec " + nClicsMot + " vues]";
	}

	


}