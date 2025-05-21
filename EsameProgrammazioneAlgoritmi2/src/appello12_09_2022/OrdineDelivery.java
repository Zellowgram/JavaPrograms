package appello12_09_2022;

public class OrdineDelivery extends Ordine {

	private String destinazione;
	private final double costoConsegna = 4.00;

	public OrdineDelivery(String destinazione) {
		this.destinazione = destinazione;
	}

	@Override
	public double getTotale() {
		double totale = 0;
		for (ProdottoOrdinato po : getComanda()) {
			totale += po.getProdotto().getPrezzoUnitario() * po.getQuantita();
		}
		return totale + costoConsegna;
	}

	public String getDestinazione() {
		return destinazione;
	}

	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	}

}
