package web.model;

import java.io.Serializable;

public class Prodotto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String nome;
	private float prezzo;
	private String descrp;
	private String dateRel;
	private String categoria;
	private int ngiocatori;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public String getDescrp() {
		return descrp;
	}

	public void setDescrp(String descrp) {
		this.descrp = descrp;
	}

	public String getDateRel() {
		return dateRel;
	}

	public void setDateRel(String dateRel) {
		this.dateRel = dateRel;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getNgiocatori() {
		return ngiocatori;
	}

	public void setNgiocatori(int ngiocatori) {
		this.ngiocatori = ngiocatori;
	}

	public Prodotto(String id, String nome, float prezzo, String descrp, String dateRel, String categoria, int ngiocatori) {
		this.id = id;
		this.prezzo = prezzo;
		this.descrp = descrp;
		this.dateRel = dateRel;
		this.categoria = categoria;
		this.ngiocatori = ngiocatori;
		this.nome = nome;
	}
	
	public Prodotto() {
		this.nome = null;
		this.id = null;
		this.prezzo = 0;
		this.descrp = null;
		this.dateRel = null;
		this.categoria = null;
		this.ngiocatori = 0;
	}

	public String toString() {
		return "Prodotto(ID=" + this.id +" Prezzo=" + this.prezzo + "DataRilascio=" + this.dateRel + " Categoria=" + this.categoria + " nGiocatori=" + this.ngiocatori + " Descrizione="+ this.descrp+ ")";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
