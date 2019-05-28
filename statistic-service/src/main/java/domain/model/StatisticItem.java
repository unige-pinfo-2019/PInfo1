package domain.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="StatisticItem")
@Data
public class StatisticItem implements Serializable {





	private static final long serialVersionUID = 1L;


	@Id
	@Column(name="Item_ID")
	String itemId;

	@Column(name="nClics_Item")		//nb de clics sur l'objet en général
	long nClicsItem;
	
	@Column(name="Category")
	Categorie category;


	public StatisticItem() {}


	public StatisticItem(String itemId, long n, Categorie categorie) {
		this.itemId = itemId;
		nClicsItem = n ;
		category = categorie ;
	}
	
	public StatisticItem(String itemId, long n) {
		this.itemId = itemId;
		nClicsItem = n ;
	}

	public StatisticItem(long n) {
		itemId = UUID.randomUUID().toString() ;
		nClicsItem = n ;
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


	public Categorie getCategory() {
		return category;
	}


	public void setCategory(Categorie category) {
		this.category = category;
	}


	@Override
	public String toString() {
		return "Statistiques [vues de l'item "+itemId+ " = " + nClicsItem + ", catégorie correspondante = " + category + "]";
	}




}
