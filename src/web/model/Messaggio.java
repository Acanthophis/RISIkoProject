package web.model;

public class Messaggio {
	private String mittente;
	private String destinatario;
	private String message;
	
	
	public Messaggio() {
		this.destinatario = null;
		this.message = null;
		this.mittente = null;
	}


	@Override
	public String toString() {
		return "Messaggio [mittente=" + mittente + ", destinatario=" + destinatario + ", message=" + message
				+  "]";
	}


	public String getDestinatario() {
		return destinatario;
	}


	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}



	public String getMittente() {
		return mittente;
	}


	public void setMittente(String mittente) {
		this.mittente = mittente;
	}
	
	
}


