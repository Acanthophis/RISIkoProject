package web.model;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

public class Carrello implements Serializable {
	private static final long serialVersionUID = 1L;
	ArrayList<ProdottoInTheCart> listaProdotti;
	String id;
	
	public Carrello() {
		id = null;
		listaProdotti = new ArrayList<ProdottoInTheCart>();
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void addElement(ProdottoInTheCart element) {
		listaProdotti.add(element);
	}
	
	/*
	public void deleteElement(ProdottoInTheCart element) {
		for(Prodotto elem : listaProdotti) {
			if(elem.equals(element)) {
				listaProdotti.remove(elem);
				break;
			}
		}
	}
	*/
	 
	public int getSize() {
		return listaProdotti.size();
	}
	
	public ArrayList<ProdottoInTheCart> getList(){
		return listaProdotti;
	}
	
	public ProdottoInTheCart getEl(int i) {
		return this.listaProdotti.get(i);
	}
	
	public ArrayList<ProdottoInTheCart> getListQuantity(){
		return listaProdotti;
	}
	
	public void svuotaCarrello() {
		this.listaProdotti.clear();
	}
	
}



