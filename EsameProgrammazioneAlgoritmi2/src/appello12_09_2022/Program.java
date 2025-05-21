package appello12_09_2022;

public class Program {

	public static void main(String[] args) {
		// Creazione FastFood
		FastFood ff = new FastFood();

		// Creazione di prodotti disponibili nel FastFood
		Prodotto hamburger = new Prodotto("Hamburger", 5.00);
		Prodotto patatine = new Prodotto("Patatine", 2.50);
		Prodotto bevanda = new Prodotto("Bevanda", 1.00);
		Prodotto piadinaKebab = new Prodotto("Piadina Kebab", 3.50);
		Prodotto paninoKebab = new Prodotto("Panino Kebab", 4.50);

		// Aggiunta dei prodotti al menù del FastFood
		ff.getProdotti().add(hamburger);
		ff.getProdotti().add(patatine);
		ff.getProdotti().add(bevanda);
		ff.getProdotti().add(piadinaKebab);
		ff.getProdotti().add(paninoKebab);

		// Mostriamo il menù
		ff.mostraPrototti();

		// Facciamo un ordine da Asporto
		OrdineAsporto oa = new OrdineAsporto("Ciccio Franco");
		oa.aggiungi(hamburger, 1);
		oa.aggiungi(patatine, 1);
		oa.aggiungi(bevanda, 1);
		//Aggiungiamo l'ordine al FastFood
		ff.getOrdini().add(oa);

		// Totale ordine Asporto:
		double totAsp = oa.getTotale();
		System.out.println("Totale Ordine Asporto: " + totAsp);

		// Facciamo un ordine da Tavolo
		OrdineTavolo ot = new OrdineTavolo("Tavolo 1", 1);
		ot.aggiungi(hamburger, 1);
		ot.aggiungi(patatine, 1);
		ot.aggiungi(bevanda, 1);
		ff.getOrdini().add(ot);
		
		// Totale ordine Tavolo:
		double totTav = ot.getTotale();
		System.out.println("Totale Ordine " + ot.getTavolo() + ": " + totTav);

		// Facciamo un ordine da Delivery
		OrdineDelivery od = new OrdineDelivery("Ciccio Franco Via della liberazione 88");
		od.aggiungi(hamburger, 1);
		od.aggiungi(patatine, 1);
		od.aggiungi(bevanda, 1);
		ff.getOrdini().add(od);

		// Totale ordine Tavolo:
		double totDel = od.getTotale();
		System.out.println("Totale Ordine Delivery: " + totDel);
		
		// Calcoliamo quanto ha incassato in totale il FastFood per ogni categoria di Ordine
		// Aggiungiamo prima qualche altro ordine
		OrdineAsporto oa1 = new OrdineAsporto("Franco Ciccio");
		oa1.aggiungi(paninoKebab, 2);
		oa1.aggiungi(patatine, 3);
		ff.getOrdini().add(oa1);

		OrdineTavolo ot1 = new OrdineTavolo("Tavolo 2", 2);
		ot1.aggiungi(paninoKebab, 2);
		ot1.aggiungi(patatine, 2);
		ot1.aggiungi(bevanda, 1);
		ff.getOrdini().add(ot1);

		OrdineDelivery od1 = new OrdineDelivery("Franco Ciccio Via della prigionia 88");
		od1.aggiungi(piadinaKebab, 2);
		od1.aggiungi(paninoKebab, 1);
		od1.aggiungi(bevanda, 3);
		ff.getOrdini().add(od1);

		// Totale ordini asporto
		double ta = ff.totaleAsporto();
		System.out.println("Totale oridni da asporto: " + ta);
		// Totale ordini tavoli
		double tt = ff.totaleTavolo();
		System.out.println("Totale oridni da tavoli: " + tt);
		// Totale ordini delivery
		double td = ff.totaleDelivery();
		System.out.println("Totale oridni da delivery: " + td);

	}

}
