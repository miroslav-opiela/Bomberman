package sk.upjs.opiela;

import java.util.ArrayList;

import sk.upjs.jpaz2.ImageTurtleShape;

/**
 * @author Miroslav Opiela
 * 
 */

public class Bonus extends ObjektHry {

	// 1 bomba
	// 2 plamen
	// 3 rosada
	private int status;
	private boolean odkryta = false;
	private Hrac hrac;

	public Bonus(int stlpec, int riadok, Pamat pamat, int status) {
		super(stlpec, riadok, pamat);
		this.status = status;
	}

	@Override
	public void naPlamen() {
		if (status == 1)
			setShape(new ImageTurtleShape(this.getClass().getResource("/bonusBomba.png")));
		if (status == 2)
			setShape(new ImageTurtleShape(this.getClass().getResource("/bonusPlamen.png")));
		if (status == 3)
			setShape(new ImageTurtleShape(this.getClass().getResource("/bonusRosada.png")));
		odkryta = true;
	}

	@Override
	public void setHrac(Hrac hrac) {
		this.hrac = hrac;
	}

	public void odstran() {
		ArrayList<Hrac> hraci = getPamat().getHraci();
		hrac.setPozicia(getStlpec(), getRiadok());
		if (status == 1) {
			hrac.setPocetBomb(hrac.getPocetBomb() + 1);
		}
		if (status == 2) {
			hrac.setDlzkaPlamena(hrac.getDlzkaPlamena() + 1);
		}
		if (status == 3) {
			int[] stlpce = new int[hraci.size()];
			int[] riadky = new int[hraci.size()];
			for (int i = 0; i < hraci.size(); i++) {
				stlpce[i] = hraci.get(i).getStlpec();
				riadky[i] = hraci.get(i).getRiadok();
			}

			for (int i = 1; i < hraci.size(); i++) {
				hraci.get(i).setPozicia(stlpce[i - 1], riadky[i - 1]);
			}
			hraci.get(0).setPozicia(stlpce[hraci.size() - 1],
					riadky[hraci.size() - 1]);
			getPamat().vlozDoPola(stlpce[0], riadky[0], hraci.get(1));
			// vymena pozicii hracov
		}
		getPamat().getPlocha().remove(this);
	}

	@Override
	public boolean isBonus() {
		return odkryta;
	}
}
