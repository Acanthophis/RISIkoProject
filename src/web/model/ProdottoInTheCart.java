package web.model;

import java.io.Serializable;

public class ProdottoInTheCart extends Prodotto{

	private static final long serialVersionUID = 1L;
	private int quantity;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public ProdottoInTheCart() {
		super();
		this.quantity = 0;
	}
}
