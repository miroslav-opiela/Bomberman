package sk.upjs.opiela;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Miroslav Opiela
 */

public class Pamat {
    private int pocetHracov;
    private ArrayList<Hrac> hraci;
    private ObjektHry[][] hraciePole;
    private Queue<Bomba> bomby;
    private Queue<PlameneBomby> plamene;
    private HraciaPlocha plocha;

    public Pamat(HraciaPlocha plocha) {
        hraciePole = new ObjektHry[14][10];
        this.plocha = plocha;
        bomby = new LinkedList<Bomba>();
        plamene = new LinkedList<PlameneBomby>();
    }

    public void vytvorHracov(int pocetHracov) {
        this.pocetHracov = pocetHracov;
        hraci = new ArrayList<Hrac>();
        hraci.add(new Hrac(KeyEvent.VK_E, KeyEvent.VK_D, KeyEvent.VK_S,
                KeyEvent.VK_F, KeyEvent.VK_A, this, 0, 0, "/bomber0hore.png",
                "/bomber0dole.png", "/bomber0vlavo.png", "/bomber0vpravo.png"));
        hraci.add(new Hrac(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT,
                KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER, this, 13, 9, "/bomber1hore.png",
                "/bomber1dole.png", "/bomber1vlavo.png", "/bomber1vpravo.png"));
        if (pocetHracov > 2) {
            hraci.add(new Hrac(KeyEvent.VK_I, KeyEvent.VK_K, KeyEvent.VK_J,
                    KeyEvent.VK_L, KeyEvent.VK_M, this, 0, 9, "/bomber2hore.png",
                    "/bomber2dole.png", "/bomber2vlavo.png", "/bomber2vpravo.png"));
        }
        if (pocetHracov > 3) {
            hraci.add(new Hrac(KeyEvent.VK_NUMPAD8, KeyEvent.VK_NUMPAD5,
                    KeyEvent.VK_NUMPAD4, KeyEvent.VK_NUMPAD6,
                    KeyEvent.VK_NUMPAD0, this, 13, 0, "/bomber3hore.png",
                    "/bomber3dole.png", "/bomber3vlavo.png", "/bomber3vpravo.png"));
        }
    }

    public ArrayList<Hrac> getHraci() {
        return hraci;
    }

    public ObjektHry[][] getHraciePole() {
        return hraciePole;
    }

    public void pridajPlamen(int stlpec, int riadok, PlameneBomby plamen) {
        plamene.offer(plamen);
    }

    public void pridajBombu(int stlpec, int riadok, Hrac hrac) {
        bomby.offer(new Bomba(stlpec, riadok, this, hrac));
        hrac.setPocetBomb(hrac.getPocetBomb() - 1);
    }

    public void vlozDoPola(int stlpec, int riadok, ObjektHry objekt) {
        hraciePole[stlpec][riadok] = objekt;
    }

    public void pevneSkatule() {
        int[] poleStlpce = new int[]{1, 3, 5, 8, 10, 12};
        int[] poleRiadky = new int[]{1, 3, 6, 8};
        for (int i = 0; i < poleStlpce.length; i++) {
            for (int j = 0; j < poleRiadky.length; j++) {
                new PevnaSkatula(poleStlpce[i], poleRiadky[j], this) {

                };

            }
        }
    }

    public void skatule(int pocetSkatul, int pocetBonusovBomb,
                        int pocetBonusovPlamenov, int pocetBonusovRosad) {
        int i = pocetSkatul;
        while (i > 0) {
            int riadok = (int) (Math.random() * 10);
            int stlpec = (int) (Math.random() * 14);
            if ((riadok <= 1 && stlpec <= 1) || (riadok >= 8 && stlpec >= 12)
                    || (pocetHracov > 2 && riadok >= 8 && stlpec <= 1)
                    || (pocetHracov > 3 && riadok <= 1 && stlpec >= 12)
                    || (hraciePole[stlpec][riadok] != null)) {
            } else {
                if (i <= pocetBonusovBomb) {
                    new Bonus(stlpec, riadok, this, 1);
                } else {
                    if (i <= pocetBonusovBomb + pocetBonusovPlamenov) {
                        new Bonus(stlpec, riadok, this, 2);
                    } else {
                        if (i <= pocetBonusovBomb + pocetBonusovPlamenov
                                + pocetBonusovPlamenov) {
                            new Bonus(stlpec, riadok, this, 3);
                        } else {
                            new ObjektHry(stlpec, riadok, this);
                        }
                    }
                }
                i--;
            }
        }
    }

    public HraciaPlocha getPlocha() {
        return plocha;
    }

    public void tiknutie(long perioda) {
        boolean opakuj = true;
        while (opakuj == true) {
            if (bomby.peek() != null) {
                if (perioda % 4 == (bomby.peek().getStartovaciCas() + 3) % 4) {
                    Bomba bomba = bomby.poll();
                    plamene.offer(new PlameneBomby(bomba.getStlpec(), bomba
                            .getRiadok(), bomba, bomba.getHrac(), this));
                    //vybuch, zmena na plamene
                } else {
                    opakuj = false;
                }
            } else {
                opakuj = false;
            }
        }
        opakuj = true;
        while (opakuj == true) {
            if (plamene.peek() != null) {
                if (perioda % 4 == (plamene.peek().getStartovaciCas()) % 4) {
                    plamene.poll().znicenie();
                    //vymazanie plamenov, dokoncenie vybuchu
                } else {
                    opakuj = false;
                }
            } else {
                opakuj = false;
            }
        }
        if (hraci != null) {
            if (hraci.size() == 1) {
                plocha.gameover(hraci.get(0));
                //koniec hry
            }
            if (hraci.size() == 0) {
                plocha.gameover(null);
                //ak je remiza
            }
        }
    }
}
