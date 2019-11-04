package sk.upjs.opiela;

import java.awt.event.MouseEvent;

import sk.upjs.jpaz2.*;

/**
 * @author Miroslav Opiela
 * 
 */
public class HraciaPlocha extends Pane {

	private Pamat pamat;
	private long perioda;
	private boolean pauza = false;
	private Turtle okenar;

	public HraciaPlocha() {
		pamat = new Pamat(this);
		setTickPeriod(1000);
	}

	public void defaultna() {
		pozadie();
		mriezka();
		for (int i = 0; i < pamat.getHraciePole().length; i++) {
			for (int j = 0; j < pamat.getHraciePole()[0].length; j++) {
				if (pamat.getHraciePole()[i][j] != null) {
					remove(pamat.getHraciePole()[i][j]);
					// vymaze vsetky objekty z plochy
				}
			}

		}
		pamat = new Pamat(this);

	}

	public void vytvorHracovASkatule(int pocetHracov) {
		pamat.vytvorHracov(pocetHracov);
		pamat.pevneSkatule();
		pamat.skatule(64, 4, 4, 4);
		for (Hrac hrac : pamat.getHraci()) {
			add(hrac);
		}
	}

	public long getPerioda() {
		return perioda;
	}

	public void pozadie() {
		Turtle t = new Turtle();
		add(t);
		t.center();
		t.setShape(new ImageTurtleShape(this.getClass().getResource("/pozadie.png")));
		t.stamp();
		remove(t);
	}

	public void uvod() {
		Turtle t = new Turtle();
		add(t);
		t.center();
		t.setShape(new ImageTurtleShape(this.getClass().getResource("/uvod.png")));
		t.stamp();
		remove(t);
	}

	public void gameover(Hrac hrac) {
		defaultna();
		Turtle t = new Turtle();
		add(t);
		t.center();
		if (hrac != null) {
			t.setShape(new ImageTurtleShape(this.getClass().getResource("/gameover.png")));
			t.stamp();
			t.setShape(new ImageTurtleShape(hrac.getObrazokDole()));
			t.setPosition(360, 330);
			t.stamp();
		} else {
			t.setShape(new ImageTurtleShape(this.getClass().getResource("/remiza.png")));
			t.stamp();
		}
		remove(t);

	}

	public void mriezka() {
		Turtle k = new Turtle();
		add(k);

		for (int i = 1; i < 16; i++) {
			k.setPosition(i * 50, 50);
			k.moveTo(i * 50, 550);
		}

		for (int i = 1; i < 12; i++) {
			k.setPosition(50, i * 50);
			k.moveTo(750, i * 50);
		}

		remove(k);
	}

	@Override
	public void setTickPeriod(long tickPeriod) {
		// TODO Auto-generated method stub
		super.setTickPeriod(tickPeriod);
	}

	@Override
	protected void onTick() {
		if (!pauza) {
			perioda++;
			pamat.tiknutie(perioda);
		}
	}

	public Pamat getPamat() {
		return pamat;
	}

	public void setPamat(Pamat pamat) {
		this.pamat = pamat;
	}

	public void pravidla() {
		okenar = new Turtle();
		okenar.setShape(new ImageTurtleShape(this.getClass().getResource("/pravidla.png")));
		add(okenar);
		okenar.center();
		pauza = true;
	}

	public boolean isPauza() {
		return pauza;
	}

	public void Pauza() {
		okenar = new Turtle();
		okenar.setShape(new ImageTurtleShape(this.getClass().getResource("/pauza.png")));
		add(okenar);
		okenar.center();
		pauza = true;
	}

	public void ovladanie() {
		okenar = new Turtle();
		okenar.setShape(new ImageTurtleShape(this.getClass().getResource("/ovladanie.png")));
		add(okenar);
		okenar.center();
		pauza = true;
	}

	@Override
	protected void onMouseClicked(int x, int y, MouseEvent detail) {
		if (pauza == true) {
			remove(okenar);
			pauza = false;
		}
	}

}
