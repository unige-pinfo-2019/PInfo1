package domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="Statistiques")
@Data
public class Statistiques implements Serializable {

	private static final long serialVersionUID = 2161064388534170538L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Item_ID")
	String itemId;
	
	@Id
	@Column(name="User_ID")
	String usrId;
	
	@Column(name="Inc_Item")
	long incItem;
	long incMob;
	long incLivres;
	long incCours;
	long incElectro;
	
	@Column(name="Mot_General")
	String motGeneral;
	
	@Column(name="Inc_General")
	long incGeneral;
	
	@Column(name="Mot_User")
	String motUser;
	
	@Column(name="Inc_User")
	long incUser;
	
	
	public Statistiques() {}
	
	
	public Statistiques(String itemId, String usrId, long incItem, long incMob, long incLivres, long incCours, long incElectro, String motGeneral, long incGeneral, String motUser, long incUser) {
		this.itemId = itemId;
		this.usrId = usrId;
		this.incItem = incItem ;
		this.incMob = incMob;
		this.incLivres = incLivres;
		this.incCours = incCours;
		this.incElectro = incElectro;
		this.motGeneral = motGeneral ;
		this.incGeneral = incGeneral ;
		this.motUser = motUser ;
		this.incUser = incUser ;
	}
	
	public Statistiques(long incItem, long incMob, long incLivres, long incCours, long incElectro, String motGeneral, long incGeneral, String motUser, long incUser) {
		this.incItem = incItem ;
		this.incMob = incMob;
		this.incLivres = incLivres;
		this.incCours = incCours;
		this.incElectro = incElectro;
		this.motGeneral = motGeneral ;
		this.incGeneral = incGeneral ;
		this.motUser = motUser ;
		this.incUser = incUser ;
	}


	public String getItemId() {
		return itemId;
	}


	public void setItemId(String itemId) {
		this.itemId = itemId;
	}


	public String getUsrId() {
		return usrId;
	}


	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}


	public long getIncItem() {
		return incItem;
	}


	public void setIncItem(long incItem) {
		this.incItem = incItem;
	}


	public long getIncMob() {
		return incMob;
	}


	public void setIncMob(long incMob) {
		this.incMob = incMob;
	}


	public long getIncLivres() {
		return incLivres;
	}


	public void setIncLivres(long incLivres) {
		this.incLivres = incLivres;
	}


	public long getIncCours() {
		return incCours;
	}


	public void setIncCours(long incCours) {
		this.incCours = incCours;
	}


	public long getIncElectro() {
		return incElectro;
	}


	public void setIncElectro(long incElectro) {
		this.incElectro = incElectro;
	}


	public String getMotGeneral() {
		return motGeneral;
	}


	public void setMotGeneral(String motGeneral) {
		this.motGeneral = motGeneral;
	}


	public long getIncGeneral() {
		return incGeneral;
	}


	public void setIncGeneral(long incGeneral) {
		this.incGeneral = incGeneral;
	}


	public String getMotUser() {
		return motUser;
	}


	public void setMotUser(String motUser) {
		this.motUser = motUser;
	}


	public long getIncUser() {
		return incUser;
	}


	public void setIncUser(long incUser) {
		this.incUser = incUser;
	}


	@Override
	public String toString() {
		return "Statistiques [vues de l'item "+itemId+ " = " + incItem + " vues mobilité=" + incMob + ", vues livres=" + incLivres
				+ ", vues cours=" + incCours + ", vues cours=" + incElectro + ", mot général le + courant=" + motGeneral + ", mot le + courant de l'utilisateur " + usrId + " = " + motUser + "]";
	}

	


}