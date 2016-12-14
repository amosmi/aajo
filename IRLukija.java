package aajobotti;

import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3IRSensor;

/**
 * <h1> IRLukija </h1>
 * IRLukija vastaa kaukos‰‰timen tilan lukemisesta. T‰ll‰ seurataan sit‰ painetaanko n‰pp‰imi‰ kaukos‰‰timest‰.
 * @author JuhaVuokko
 *
 */

public class IRLukija extends Thread {
	private EV3IRSensor irsensori;
	private int komento;
	private boolean lopeta;
	
	/**
	 * Luodaan IRLukija olio. 
	 * @param sensori EV3IRSensor Tarvittava sensoriolio kaukos‰‰timen tilan seuraamiseen.
	 */
	public IRLukija(EV3IRSensor sensori){
		this.irsensori = sensori;
		this.lopeta = false;
	}
	
	/**
	 * T‰m‰ metodi ylikirjoittaa Thread-luokan run()-metodin. T‰m‰ seuraa irsensorin avulla kaukos‰‰timen tilaa. 
	 */
	public void run(){
		while (!this.lopeta){
			try{
				this.komento = this.irsensori.getRemoteCommand(0);
			}catch(Exception e){
				LCD.drawString(e.toString(),0,0);
			}
			
		}
	}
	
	/**
	 * T‰m‰ metodi v‰litt‰‰ int-muuttujana, mik‰ on kaukos‰‰timen tila: painetaanko jotain n‰pp‰int‰ tai ei.
	 * @return int n‰pp‰inkoodi int-muodossa.
	 */
	public int annaKomento(){
		return this.komento;
	}
	
	/**
	 * T‰t‰ metodia tarvitaan threadin pys‰ytt‰miseksi. Ilman t‰m‰n k‰yttˆ‰, ohjelmaa ei saada sammutettua.
	 */
	public void lopeta(){
		this.lopeta = true;
	}
	
}
