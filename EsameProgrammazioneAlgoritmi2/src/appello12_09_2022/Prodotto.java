package appello12_09_2022;

public class Prodotto {
	private String descrizione;
	private double prezzoUnitario;

	public Prodotto(String descrizione, double prezzoUnitario) {
		this.descrizione = descrizione;
		this.prezzoUnitario = prezzoUnitario;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public double getPrezzoUnitario() {
		return prezzoUnitario;
	}

}
