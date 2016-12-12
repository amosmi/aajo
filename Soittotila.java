package aajobotti;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.utility.Delay;
import lejos.utility.TextMenu;

/**
 * Soittotila vas
 * @author JuhaVuokko
 *
 */

public class Soittotila {
	
	private EV3IRSensor irsensori;
	private IRLukija lukija;
	private BrickLukija bricklukija;
	private Soittaja soittaja;
	private Tekstivalikko tekstivalikko;
	private String[] valikko;
	private int valitsin;
	
	public Soittotila(IRLukija lukija, BrickLukija bricklukija){
	
		this.lukija = lukija;
		this.bricklukija = bricklukija;
		this.soittaja = new Soittaja(60);
		soittaja.asetaInstrumentti(0);
		valikko = new String[4];
		valikko[0] = "Piano";
		valikko[1] = "Viulu";
		valikko[2] = "Xylofoni";
		valikko[3] = "Palaa";
		tekstivalikko = new Tekstivalikko("Soittaja",valikko);
		valitsin =tekstivalikko.annaKohta();
		
		
		
	}
	
	public void suorita(){
	
		int nappainkoodi;
		boolean lopeta = false;
		tekstivalikko.esitaValikko();
		
		 do{
			 nappainkoodi = bricklukija.annaKoodi();
			 switch(nappainkoodi){
			 case Button.ID_DOWN:
				 tekstivalikko.alas();
				 tekstivalikko.esitaValikko();
				 valitsin = tekstivalikko.annaKohta();
				 break;
			 case Button.ID_UP:
				 tekstivalikko.ylos();
				 tekstivalikko.esitaValikko();
				 valitsin = tekstivalikko.annaKohta();
				 break;
			 case Button.ID_ENTER:
			 	switch (this.valitsin){
			 	case 0:
			 		tekstivalikko.tyhjennaNaytto();
			 		soitintila(0,100,valikko[0]);
			 		tekstivalikko.esitaValikko();
			 		break;
			 	case 1:
			 		tekstivalikko.tyhjennaNaytto();
			 		soitintila(1,200,valikko[1]);
			 		tekstivalikko.esitaValikko();
			 		break;
			 	case 2:
			 		tekstivalikko.tyhjennaNaytto();
			 		soitintila(2,10,valikko[2]);
			 		tekstivalikko.esitaValikko();
			 		break;
			 	case 3:
			 		tekstivalikko.tyhjennaNaytto();
			 		lopeta = true;
			 		break;
			 	}
			 	break;
			 }
		
		}while (!lopeta);
		
		
	}
	
	public void soitintila(int soitinvalinta, int kesto, String soitin){
		
		int komento = 0;
		int saatoKomento = 0;
		boolean lopeta = false;
		soittaja.asetaInstrumentti(soitinvalinta);
		soittaja.asetaKesto(kesto);
		this.paivitaNaytto(soitin, soittaja.annaAanenVoimakkuus());
		
		
		do{
			
			komento = lukija.annaKomento();
			saatoKomento = bricklukija.annaKoodi();
			switch (komento){
			case 1:
				soittaja.soita(200);
				break;
			case 2:
				soittaja.soita(400);
				break;
			case 3:
				soittaja.soita(600);
				break;
			case 4:
				soittaja.soita(800);
				break;
			case 5:
				soittaja.soita(1000);
				break;
			case 6:
				soittaja.soita(1200);
				break;
			case 7:
				soittaja.soita(1400);
				break;
			case 8:
				soittaja.soita(1600);
				break;
			case 10:
				soittaja.soita(1800);
				break;
			case 11:
				soittaja.soita(2000);
				break;
			
			}	 
			switch(saatoKomento){
		
			case Button.ID_DOWN:
				soittaja.muutaAanenVoimakkuutta(-10);
				this.paivitaNaytto(soitin, soittaja.annaAanenVoimakkuus());
				break;
			case Button.ID_UP:
				soittaja.muutaAanenVoimakkuutta(10);
				this.paivitaNaytto(soitin, soittaja.annaAanenVoimakkuus());
				break;
			case Button.ID_ESCAPE:
				LCD.clear();
				lopeta = true;
				break;
			}
		
		} while (!lopeta);
	}
	
	public void paivitaNaytto(String soitin, int aanenvoimakkuus){
		LCD.drawString(soitin, 0, 0);
		LCD.drawString("Vol: "+ aanenvoimakkuus, 0, 1);
	}
		

}
