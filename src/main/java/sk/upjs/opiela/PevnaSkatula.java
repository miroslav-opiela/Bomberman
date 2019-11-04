package sk.upjs.opiela;

import sk.upjs.jpaz2.ImageTurtleShape;


/**
 * @author Miroslav Opiela
 * 
 */

public class PevnaSkatula extends ObjektHry{

	public PevnaSkatula(int stlpec, int riadok, Pamat pamat) {
		super(stlpec, riadok, pamat);
		setShape(new ImageTurtleShape(this.getClass().getResource("/pevnaSkatula.png")));
		pamat.getPlocha().add(this);
	}

	@Override
	public boolean isPevnaSkatula() {
		// TODO Auto-generated method stub
		return true;
	}

}
