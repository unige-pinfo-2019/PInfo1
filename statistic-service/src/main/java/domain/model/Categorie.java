package domain.model;

public enum Categorie {

	LIVRE("LIVRE"),
	MOBILITE("MOBILITE"), 
	ELECTRONIQUE("ELECTRONIQUE"),
	COURS("COURS"), 
	MOBILIER("MOBILIER"),
	AUTRE("AUTRE");
	
	
	private final String name ;
	
	private Categorie(String str) {
		name = str ;
	}
	
	public boolean equalsName(String str) {
		return name.equals(str);
	}
	
	@Override
	public String toString() {
		return name ;
	}

	public static Categorie lookup(String str) {
		switch (str) {
		case "MOBILITE":
			return MOBILITE;
		case "MOBILIER":
			return MOBILIER;
		case "ELECTRONIQUE":
			return ELECTRONIQUE;
		case "COURS":
			return COURS;
		case "LIVRE":
			return LIVRE;
		case "AUTRE":
			return AUTRE;
		default:
			return null ;
		}
	}
	
}
