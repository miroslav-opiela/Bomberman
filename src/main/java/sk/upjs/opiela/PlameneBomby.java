package sk.upjs.opiela;

/**
 * @author Miroslav Opiela
 * 
 */

public class PlameneBomby {

	private ObjektHry[] plamene;
	private Hrac hrac;
	private int dlzkaPlamena;
	private ObjektHry[][] pole;
	private Pamat pamat;
	private int index = 1;
	private long startovaciCas;

	public PlameneBomby(int stlpec, int riadok, Bomba bomba, Hrac hrac,
			Pamat pamat) {
		bomba.naPlamen();
		this.hrac = hrac;
		this.pamat = pamat;
		this.startovaciCas = bomba.getStartovaciCas();
		pole = pamat.getHraciePole();
		dlzkaPlamena = bomba.getDlzkaPlamena();
		plamene = new ObjektHry[1 + dlzkaPlamena * 4];
		plamene[0] = bomba;
		plamenNaStranu(stlpec, riadok, 1, 0);
		plamenNaStranu(stlpec, riadok, -1, 0);
		plamenNaStranu(stlpec, riadok, 0, 1);
		plamenNaStranu(stlpec, riadok, 0, -1);
	}

	public void znicenie() {
		hrac.setPocetBomb(hrac.getPocetBomb() + 1);
		for (int j = 0; j < plamene.length; j++) {
			if (plamene[j] != null) {
				if (!plamene[j].isBonus()) {
					plamene[j].odstran();// ak je bonus nech tam ostane
				}
			}
		}
	}

	public void plamenNaStranu(int stlpec, int riadok, int s, int r) {
		int aktualnySlpec = stlpec + s;
		int aktualnyRiadok = riadok + r;
		boolean pokracuj = true;
		while (aktualnyRiadok >= 0 && aktualnyRiadok <= 9 && aktualnySlpec >= 0
				&& aktualnySlpec <= 13
				&& Math.abs(riadok - aktualnyRiadok) <= dlzkaPlamena
				&& Math.abs(stlpec - aktualnySlpec) <= dlzkaPlamena
				&& pokracuj == true) {
			if (pole[aktualnySlpec][aktualnyRiadok] != null) {
				if (pole[aktualnySlpec][aktualnyRiadok].isBomba()) {
					pole[aktualnySlpec][aktualnyRiadok]
							.setStartovaciCas(startovaciCas);
				}// ak stoji v ceste ina bomba nech ihned tiez vybuchne
				if (pole[aktualnySlpec][aktualnyRiadok].isBonus()) {
					pamat.getPlocha().remove(
							pole[aktualnySlpec][aktualnyRiadok]);
					pamat.vlozDoPola(aktualnySlpec, aktualnyRiadok, null);
					pokracuj = false;
				}
			}
			if (pole[aktualnySlpec][aktualnyRiadok] == null) {
				new ObjektHry(aktualnySlpec, aktualnyRiadok, pamat, false);
				// ak tam nic nie je nech vytvori novy objekt hry ktory sa
				// premeni na plamen
			} else {
				pokracuj = false;

			}
			if (!pole[aktualnySlpec][aktualnyRiadok].isPevnaSkatula()) {
				pole[aktualnySlpec][aktualnyRiadok].naPlamen();
				plamene[index] = pole[aktualnySlpec][aktualnyRiadok];
				index++;
			}
			aktualnyRiadok = aktualnyRiadok + r;
			aktualnySlpec = aktualnySlpec + s;
		}
	}

	public long getStartovaciCas() {
		return startovaciCas;
	}

}
