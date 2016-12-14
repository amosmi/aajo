package aajobotti;


import lejos.hardware.Button;
import lejos.utility.Delay;

/**
 * ValoS-luokan avulla voi vilkutella brickin ledej‰.
 * @author Amos Miikkulainen
 *
 */

public class ValoS{
	
	/**
	 * T‰m‰n avulla voi k‰ynnist‰‰ ledvalot. Int-arvolla nolla ei tapahdu mit‰‰n. 
	 * @param valokoodi int
	 */
	public void vilkuta(int valokoodi) {
		
		Button.LEDPattern(valokoodi);
			
	}
	
	/**
	 * T‰m‰ vastaa vilkuta(0), t‰m‰ pys‰ytt‰‰ ledvalot.
	 */
	public void lopetaVilkutus(){
		Button.LEDPattern(0);
	}
	
	/**
	 * T‰m‰ vilkuttaa ledvaloja tietyn kaavan mukaan.
	 */
	public void loppuvilkutus(){
		Button.LEDPattern(7);
		Delay.msDelay(200);
		Button.LEDPattern(8);
		Delay.msDelay(200);
		Button.LEDPattern(9);
		Delay.msDelay(200);
		Button.LEDPattern(0);
	}
	

}