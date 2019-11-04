package sk.upjs.opiela;

import sk.upjs.jpaz2.ImageTurtleShape;

/**
 * @author Miroslav Opiela
 * 
 */

public class Bomba extends ObjektHry {

	private long startovaciCas;
	private int dlzkaPlamena;
	private Hrac hrac;

	public Bomba(int stlpec, int riadok, Pamat pamat, Hrac hrac) {
		super(stlpec, riadok, pamat);
		this.setHrac(hrac);
		this.dlzkaPlamena = hrac.getDlzkaPlamena();
		setShape(new ImageTurtleShape("src/main/resources/bomba.png"));
		pamat.getPlocha().add(this);
		startovaciCas = (int) (pamat.getPlocha().getPerioda() % 4);
	}

	public long getStartovaciCas() {
		return startovaciCas;
	}

	@Override
	public void setStartovaciCas(long startovaciCas) {
		this.startovaciCas = startovaciCas;
	}

	public int getDlzkaPlamena() {
		return dlzkaPlamena;
	}

	public void setHrac(Hrac hrac) {
		this.hrac = hrac;
	}

	public Hrac getHrac() {
		return hrac;
	}

	@Override
	public boolean isBomba() {
		return true;
	}

}
