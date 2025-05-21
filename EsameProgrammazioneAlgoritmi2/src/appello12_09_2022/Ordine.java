package appello12_09_2022;

import java.util.ArrayList;

public abstract class Ordine {
	private ArrayList<ProdottoOrdinato> comanda;

	public Ordine() {
		this.comanda = new ArrayList<>();
	}

	public void aggiungi(Prodotto p, int quantita) {
		comanda.add(new ProdottoOrdinato(p, quantita));
	}

	public abstract double getTotale();

	public ArrayList<ProdottoOrdinato> getComanda() {
		return comanda;
	}

}
