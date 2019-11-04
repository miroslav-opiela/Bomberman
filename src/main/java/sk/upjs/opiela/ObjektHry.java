package sk.upjs.opiela;

import sk.upjs.jpaz2.ImageTurtleShape;
import sk.upjs.jpaz2.Turtle;

/**
 * @author Miroslav Opiela
 * 
 */

public class ObjektHry extends Turtle {

	private int riadok = -1;
	private int stlpec = -1;
	private Pamat pamat;
	boolean vlozena = true;
	static final int dlzkaPolicka = 50;

	public ObjektHry(int stlpec, int riadok, Pamat pamat) {
		setPosition(stlpec * 50 + 75, riadok * 50 + 75);
		this.riadok = riadok;
		this.stlpec = stlpec;
		this.pamat = pamat;
		pamat.vlozDoPola(stlpec, riadok, this);
		setShape(new ImageTurtleShape(this.getClass().getResource("/skatula.png")));
		pamat.getPlocha().add(this);
	}

	public ObjektHry(int stlpec, int riadok, Pamat pamat,
			boolean vlozena) {
		setPosition(stlpec * 50 + 75, riadok * 50 + 75);
		this.riadok = riadok;
		this.stlpec = stlpec;
		this.pamat = pamat;
		pamat.vlozDoPola(stlpec, riadok, this);
		setVlozena(false);
	}//ak sa ma ihned zmenit na plamen

	public boolean isVlozena() {
		return vlozena;
	}

	public boolean isBonus() {
		return false;
	}
	
	public boolean isBomba(){
		return false;
	}

	public Pamat getPamat(){
		return pamat;
	}
	
	public void setVlozena(boolean vlozena) {
		this.vlozena = vlozena;
	}

	public void naPlamen() {
		setShape(new ImageTurtleShape(this.getClass().getResource("/plamen.png")));
		if (!vlozena) {
			pamat.getPlocha().add(this);
		}
	}

	public void odstran() {
		pamat.vlozDoPola(stlpec, riadok, null);
		pamat.getPlocha().remove(this);
	}

	public boolean isPevnaSkatula() {
		return false;
	}

	public int getRiadok() {
		return riadok;
	}

	public int getStlpec() {
		return stlpec;
	}
	
	public void setHrac(Hrac hrac){
	}
	
	public void setStartovaciCas(long startovaciCas){
		
	}

	public void setPozicia(int stlpec, int riadok) {
		if (stlpec > -1) {
			pamat.vlozDoPola(this.stlpec, this.riadok, null);
		}
		this.riadok = riadok;
		this.stlpec = stlpec;
		setPosition(stlpec * 50 + 75, riadok * 50 + 75);
		pamat.vlozDoPola(stlpec, riadok, this);
	}



}
