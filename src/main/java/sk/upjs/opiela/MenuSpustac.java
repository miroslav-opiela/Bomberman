package sk.upjs.opiela;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import sk.upjs.jpaz2.*;

/**
 * @author Miroslav Opiela
 * 
 */

public class MenuSpustac extends JFrame {

	private static final long serialVersionUID = 1L;
	HraciaPlocha plocha;

	public static void main(String[] args) {
		new MenuSpustac();

	}

	/**
	 * vytvorenie hlavneho okna s menu a plochou
	 */
	public MenuSpustac() {
		super("Bomberman");
		setSize(805, 650);
		setResizable(false);
		setLocationRelativeTo(getRootPane());// zarovnanie na stred obrazovky
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Problem s nastavenim vzhladu menu");
		}// nech to ma vzhlad ako windowsacke okno

		plocha = new HraciaPlocha();
		JPAZPanel volnaPlocha = new JPAZPanel(plocha);
		add(volnaPlocha);
		plocha.setWidth(800);
		plocha.setHeight(600);
		plocha.uvod();

		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);

		JMenu hra = new JMenu("Hra");
		menubar.add(hra);
		JMenu pomocnik = new JMenu("PomocnÌk");
		menubar.add(pomocnik);

		JMenuItem novaHra2 = new JMenuItem("Nov· hra pre 2 hr·Ëov");
		hra.add(novaHra2);
		JMenuItem novaHra3 = new JMenuItem("Nov· hra pre 3 hr·Ëov");
		hra.add(novaHra3);
		JMenuItem novaHra4 = new JMenuItem("Nov· hra pre 4 hr·Ëov");
		hra.add(novaHra4);
		JMenuItem koniec = new JMenuItem("Koniec");
		hra.add(koniec);

		JMenuItem pravidla = new JMenuItem("Pravidl· hry");
		pomocnik.add(pravidla);
		JMenuItem ovladanie = new JMenuItem("Ovl·danie");
		pomocnik.add(ovladanie);

		novaHra2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (plocha.isPauza()) {
					Toolkit.getDefaultToolkit().beep();
				} else {
					plocha.defaultna();
					plocha.vytvorHracovASkatule(2);
					//Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_NUM_LOCK, Boolean.TRUE);
				}
			}
		});

		novaHra3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (plocha.isPauza()) {
					Toolkit.getDefaultToolkit().beep();
				} else {
					plocha.defaultna();
					plocha.vytvorHracovASkatule(3);
					//Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_NUM_LOCK, Boolean.TRUE);
				}
			}
		});

		novaHra4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (plocha.isPauza()) {
					Toolkit.getDefaultToolkit().beep();
				} else {
					plocha.defaultna();
					plocha.vytvorHracovASkatule(4);
					//Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_NUM_LOCK, Boolean.TRUE);
				}
			}
		});

		koniec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});

		ovladanie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (plocha.isPauza()) {
					Toolkit.getDefaultToolkit().beep();
				} else {
					plocha.ovladanie();
				}
			}
		});

		pravidla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (plocha.isPauza()) {
					Toolkit.getDefaultToolkit().beep();
				} else {
					plocha.pravidla();
				}
			}
		});

		setVisible(true);

		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (plocha.getPamat().getHraci() == null || plocha.isPauza()) {
					Toolkit.getDefaultToolkit().beep();// vystrazny zvuk
				} else {
					if (e.getKeyCode() == KeyEvent.VK_SPACE) {
						plocha.Pauza();
					}
					for (Hrac hrac : plocha.getPamat().getHraci()) {
						if (e.getKeyCode() == hrac.getkHore()) {
							hrac.hore();
						}
						if (e.getKeyCode() == hrac.getkDole()) {
							hrac.dole();
						}
						if (e.getKeyCode() == hrac.getkVlavo()) {
							hrac.vlavo();
						}
						if (e.getKeyCode() == hrac.getkVpravo()) {
							hrac.vpravo();
						}
						if (e.getKeyCode() == hrac.getkBomba()) {
							hrac.bombaStlacena();
						}
					}
				}
			}

		});
	}

}
