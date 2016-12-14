package aajobotti;

import lejos.hardware.Button;
import lejos.utility.Delay;

/**
 * BrickLukija hoitaa brickin näppäinten painnallusten seuraamisen. BrickLukija on Thread-luokan aliluokka ja se hyödyntää
 * ylikirjoittaa Thread-luokan run-metodin.
 * @author JuhaVuokko
 *
 */
public class BrickLukija extends Thread{
	
	private boolean lopeta;
	private boolean rekisteroity;
	private int nappainKoodi;
	
	/**
	 * Luodaan Bricklukija-olio. Aluksi nappainkoodi on asetettu arvoon 0. Boolean-muuttujat lopeta ja rekisteroitu saa
	 * arvot false.
	 */
	public BrickLukija(){
		
		this.lopeta = false;
		this.rekisteroity = false;
		this.nappainKoodi = 0;
		
	}
	
	/**
	 * Ylikirjoittaa Thread-luokan run()-metodin. Tämä hoitaa Brickin näppäinten seurannan.
	 */
	public void run(){
		while(!this.lopeta){
			this.nappainKoodi = Button.readButtons();
		}
	}
	
	/**
	 * Lopeta-metodia tarvitaan run-metodin
	 */
	public void lopeta(){
		this.lopeta = true;
	}
	
	/**
	 * Tämä palauttaa painalluksen mukaisen int-arvoisen koodin, painalluksen yhteydessä palauttaa vain yhden kerran painalluksen
	 * koodia, muuten palautaa nollia.
	 * @return int Näppäinpainallusten koodit.
	 */
	public int annaKoodi(){
		if (this.rekisteroity == false && this.nappainKoodi != 0){
			this.rekisteroity = true;
			return this.nappainKoodi;
		}else{
			if (this.rekisteroity == true && this.nappainKoodi == 0){
				this.rekisteroity = false;
			}
			return 0;
		}
	}

}
