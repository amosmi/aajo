package musa;



import lejos.utility.Delay;
import lejos.hardware.Button;

public class valoS {
boolean lopetaValotus=false;
	public void valoShow() {
		
		while (lopetaValotus=false) {
		Button.LEDPattern(7);
		Delay.msDelay(200);
		Button.LEDPattern(8);
		Delay.msDelay(200);
		Button.LEDPattern(9);
		Delay.msDelay(200);
		}

	}
	public void lopeta() {
		lopetaValotus = true;
}


}
