package sk.upjs.opiela;

import sk.upjs.jpaz2.ImageTurtleShape;

/**
 * @author Miroslav Opiela
 * 
 */

public class Hrac extends ObjektHry {

	private int kHore;
	private int kDole;
	private int kVlavo;
	private int kVpravo;
	private int kBomba;
	//ovladanie
	
	private int pocetBomb = 1;
	private int riadokBomby = -1;
	private int stlpecBomby = -1;
	private int dlzkaPlamena = 1;
	
	private String obrazokHore;
	private String obrazokDole;
	private String obrazokVlavo;
	private String obrazokVpravo;
	//obrazky na zobrazenie

	public Hrac(int kHore, int kDole, int kVlavo, int kVpravo, int kEnter,
			Pamat pamat, int stlpec, int riadok, String obrazokHore, String obrazokDole, String obrazokVlavo, String obrazokVpravo) {
		super(stlpec, riadok, pamat);
		this.kHore = kHore;
		this.kDole = kDole;
		this.kVlavo = kVlavo;
		this.kVpravo = kVpravo;
		this.kBomba = kEnter;
		this.obrazokHore = obrazokHore;
		this.obrazokDole = obrazokDole;
		this.obrazokVlavo = obrazokVlavo;
		this.obrazokVpravo = obrazokVpravo;
		setShape(new ImageTurtleShape(obrazokDole));
		penUp();
	}

	public void setDlzkaPlamena(int dlzkaPlamena) {
		this.dlzkaPlamena = dlzkaPlamena;
	}

	public int getDlzkaPlamena() {
		return dlzkaPlamena;
	}

	public int getkHore() {
		return kHore;
	}

	public int getkDole() {
		return kDole;
	}

	public int getkVlavo() {
		return kVlavo;
	}

	public int getkVpravo() {
		return kVpravo;
	}

	public int getkBomba() {
		return kBomba;
	}

	public int getPocetBomb() {
		return pocetBomb;
	}

	public void setPocetBomb(int pocetBomb) {
		this.pocetBomb = pocetBomb;
	}

	public String getObrazokDole() {
		return obrazokDole;
	}

	public void hore() {
		setShape(new ImageTurtleShape(this.getClass().getResource(obrazokHore)));
		if (getRiadok() > 0) {
			if (getPamat().getHraciePole()[getStlpec()][getRiadok() - 1] == null) {
				setPozicia(getStlpec(), getRiadok() - 1);//posun hore
			} else {
				if (getPamat().getHraciePole()[getStlpec()][getRiadok() - 1]
						.isBonus()) {
					getPamat().getHraciePole()[getStlpec()][getRiadok() - 1]
							.setHrac(this);
					getPamat().getHraciePole()[getStlpec()][getRiadok() - 1]
							.odstran();//ak tam je odkryty bonus

				}
			}
		}
		aktivaciaBomby();
	}

	public void dole() {
		setShape(new ImageTurtleShape(this.getClass().getResource(obrazokDole)));
		if (getRiadok() < 9) {
			if (getPamat().getHraciePole()[getStlpec()][getRiadok() + 1] == null) {
				setPozicia(getStlpec(), getRiadok() + 1);
			} else {
				if (getPamat().getHraciePole()[getStlpec()][getRiadok() + 1]
						.isBonus()) {
					getPamat().getHraciePole()[getStlpec()][getRiadok() + 1]
							.setHrac(this);
					getPamat().getHraciePole()[getStlpec()][getRiadok() + 1]
							.odstran();

				}
			}
		}
		aktivaciaBomby();
	}

	public void vlavo() {
		setShape(new ImageTurtleShape(this.getClass().getResource(obrazokVlavo)));
		if (getStlpec() > 0) {
			if (getPamat().getHraciePole()[getStlpec() - 1][getRiadok()] == null) {
				setPozicia(getStlpec() - 1, getRiadok());
			} else {
				if (getPamat().getHraciePole()[getStlpec() - 1][getRiadok()]
						.isBonus()) {
					getPamat().getHraciePole()[getStlpec() - 1][getRiadok()]
							.setHrac(this);
					getPamat().getHraciePole()[getStlpec() - 1][getRiadok()]
							.odstran();

				}
			}
		}
		aktivaciaBomby();
	}

	public void vpravo() {
		setShape(new ImageTurtleShape(this.getClass().getResource(obrazokVpravo)));
		if (getStlpec() < 13) {
			if (getPamat().getHraciePole()[getStlpec() + 1][getRiadok()] == null) {
				setPozicia(getStlpec() + 1, getRiadok());
			} else {
				if (getPamat().getHraciePole()[getStlpec() + 1][getRiadok()]
						.isBonus()) {
					getPamat().getHraciePole()[getStlpec() + 1][getRiadok()]
							.setHrac(this);
					getPamat().getHraciePole()[getStlpec() + 1][getRiadok()]
							.odstran();

				}
			}
		}
		aktivaciaBomby();
	}

	public void bombaStlacena() {
		if (pocetBomb > 0) {
			stlpecBomby = getStlpec();
			riadokBomby = getRiadok();
		}
	}

	public void aktivaciaBomby() {
		if (stlpecBomby > -1 && riadokBomby > -1) {
			if (getStlpec() != stlpecBomby || getRiadok() != riadokBomby) {
				getPamat().pridajBombu(stlpecBomby, riadokBomby, this);
				stlpecBomby = -1;
				riadokBomby = -1;
			}
		}
	}

	@Override
	public void odstran() {
		super.odstran();
		stlpecBomby = -1;
		riadokBomby = -1;
		getPamat().getHraci().remove(this);
	}
}
