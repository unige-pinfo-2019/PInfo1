package domain.model;

import java.io.Serializable;
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

	@Column(name="nClics_Item")		//nb de clics sur l'objet en g�n�ral
	long nClicsItem;


	public StatisticItem() {}


	public StatisticItem(String itemId, long n) {
		this.itemId = itemId;
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


	@Override
	public String toString() {
		return "Statistiques [vues de l'item "+itemId+ " = " + nClicsItem + "]";
	}




}
