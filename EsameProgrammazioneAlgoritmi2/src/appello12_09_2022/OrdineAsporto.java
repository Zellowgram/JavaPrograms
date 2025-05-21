package appello12_09_2022;

public class OrdineAsporto extends Ordine {

	private String nominativo;

	public OrdineAsporto(String nominativo) {
		this.nominativo = nominativo;
	}

	@Override
	public double getTotale() {
		double totale = 0;

		for (ProdottoOrdinato po : getComanda()) {
			totale += po.getProdotto().getPrezzoUnitario() * po.getQuantita();
		}

		return totale;
	}

	public String getNominativo() {
		return nominativo;
	}

	public void setNominativo(String nominativo) {
		this.nominativo = nominativo;
	}

}
