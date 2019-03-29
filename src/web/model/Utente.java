package web.model;

import java.io.Serializable;

public class Utente implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String psw;
	private String name;
	private String surname;
	private String role;
	private Casella casellaPostale;
	private String email;
	private float saldo;
	private Carrello cart = null;
	
	public Utente() {
		this.id = null;
		this.psw = null;
		this.name = null;
		this.surname = null;
		this.setCasellaPostale(null);
		this.setRole(null);
		this.setEmail(null);
		this.setSaldo(0);
		this.cart = null;
	}
	
	@Override
	public String toString() {
		return "Id: " + id + ". Psw: " + psw + ". Name: " + name + ". Surname: " + surname + ".Role: " + role
				+ ". Email= " + email + ". Saldo= " + saldo + ".";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Utente(String id, String psw, String name, String surname) {
		super();
		this.id = id;
		this.psw = psw;
		this.name = name;
		this.surname = surname;
	}

	public Casella getCasellaPostale() {
		return casellaPostale;
	}

	public void setCasellaPostale(Casella casellaPostale) {
		this.casellaPostale = casellaPostale;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Carrello getCart() {
		return cart;
	}

	public void setCart(Carrello cart) {
		this.cart = cart;
	}
}
