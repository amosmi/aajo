package aajobotti;


import lejos.hardware.Button;
import lejos.utility.Delay;

public class ValoS{
	
	
	
	public void vilkuta(int valokoodi) {
		
		Button.LEDPattern(valokoodi);
			
	}
	
	public void lopetaVilkutus(){
		Button.LEDPattern(0);
	}
	
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