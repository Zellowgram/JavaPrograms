package appello12_09_2022;

public class OrdineTavolo extends Ordine {

	private String tavolo;
	private int coperti;
	private final double costoCoperto = 1.80;

	public OrdineTavolo(String tavolo, int coperti) {
		this.tavolo = tavolo;
		this.coperti = coperti;
	}

	@Override
	public double getTotale() {
		double totale = 0;
		for (ProdottoOrdinato po : getComanda()) {
			totale += po.getProdotto().getPrezzoUnitario() * po.getQuantita();
		}
		return totale + (coperti * costoCoperto);
	}

	public String getTavolo() {
		return tavolo;
	}

	public void setTavolo(String tavolo) {
		this.tavolo = tavolo;
	}

	public int getCoperti() {
		return coperti;
	}

	public void setCoperti(int coperti) {
		this.coperti = coperti;
	}

}
