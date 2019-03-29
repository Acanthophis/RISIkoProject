package web.model;

import java.util.ArrayList;

public class Casella {
	private String idP;
	private ArrayList<Messaggio> inbox;
	
	public Casella(String idP) {
		this.idP = idP;
		this.inbox.add(new Messaggio());
	}
	public Casella() {
		this.idP = null;
		this.inbox = new ArrayList<>();
	}

	public String getIdP() {
		return idP;
	}

	public void setIdP(String idP) {
		this.idP = idP;
	}

	public ArrayList<Messaggio> getInbox() {
		return inbox;
	}

	public void setInbox(ArrayList<Messaggio> inbox) {
		this.inbox = inbox;
	}
	
	public Messaggio getMessage(int i) {
		return inbox.get(i);
	}
	
	public void addMessaggio(Messaggio mex) {
		this.inbox.add(mex);
	}
	
	
}

