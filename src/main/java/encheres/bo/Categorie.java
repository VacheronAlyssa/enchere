package encheres.bo;

import java.io.Serializable;

public class Categorie implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int noCategorie;
	private String nomCategorie;

	public Categorie() {
	}

	public Categorie(int noCategorie, String nomCategorie) {
		this.noCategorie = noCategorie;
		this.nomCategorie = nomCategorie;
	}

	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getNomCategorie() {
		return nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

	@Override
	public String toString() {
		return "Categorie{" + "noCategorie=" + noCategorie + ", nomCategorie='" + nomCategorie + '\'' + '}';
	}
}
