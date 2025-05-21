package appello12_09_2022;

import java.util.ArrayList;

public class FastFood {

	private ArrayList<Prodotto> prodotti;
	private ArrayList<Ordine> ordini;

	public FastFood() {
		this.prodotti = new ArrayList<>();
		this.ordini = new ArrayList<>();
	}

	public double totaleAsporto() {
		double totaleAsp = 0;
		ArrayList<OrdineAsporto> ordiniAsporto = new ArrayList<>();
		for (Ordine o : ordini) {
			if (o instanceof OrdineAsporto) {
				ordiniAsporto.add((OrdineAsporto) o);
			}
		}
		for (OrdineAsporto oa : ordiniAsporto) {
			totaleAsp += oa.getTotale();
		}
		return totaleAsp;
	}

	public double totaleTavolo() {
		double totaleTav = 0;
		ArrayList<OrdineTavolo> ordiniTavoli = new ArrayList<>();
		for (Ordine o : ordini) {
			if (o instanceof OrdineTavolo) {
				ordiniTavoli.add((OrdineTavolo) o);
			}
		}
		for (OrdineTavolo oa : ordiniTavoli) {
			totaleTav += oa.getTotale();
		}

		return totaleTav;
	}

	public double totaleDelivery() {
		double totaleDel = 0;
		ArrayList<OrdineDelivery> ordiniDelivery = new ArrayList<>();
		for (Ordine o : ordini) {
			if (o instanceof OrdineDelivery) {
				ordiniDelivery.add((OrdineDelivery) o);
			}
		}
		for (OrdineDelivery od : ordiniDelivery) {
			totaleDel += od.getTotale();
		}
		return totaleDel;
	}

	public Prodotto piuVenduto() {
		Prodotto piuVenduto = null;
		int maxVendite = 0;

		for (Prodotto p : prodotti) {
			int venditeTotali = 0;

			for (Ordine ordine : ordini) {
				for (ProdottoOrdinato po : ordine.getComanda()) {
					if (po.getProdotto().equals(p)) {
						venditeTotali += po.getQuantita();
					}
				}
			}

			if (venditeTotali > maxVendite) {
				maxVendite = venditeTotali;
				piuVenduto = p;
			}
		}

		return piuVenduto;
	}

	public void mostraPrototti() {
		for (Prodotto p : prodotti) {
			System.out.println("Prodotto: " + p.getDescrizione() + " Prezzo: " + p.getPrezzoUnitario());
		}
	}

	public ArrayList<Prodotto> getProdotti() {
		return prodotti;
	}

	public ArrayList<Ordine> getOrdini() {
		return ordini;
	}

}
