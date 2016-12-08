package musa;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.utility.Delay;
import lejos.utility.TextMenu;

public class Soittotila {
	
	private EV3IRSensor irsensori;
	private IRLukija lukija;
	private BrickLukija saadot;
	private Soittaja soittaja;
	private Tekstivalikko tekstivalikko;
	private String[] valikko;
	private int valitsin;
	
	public Soittotila(IRLukija lukija, BrickLukija bricklukija){
	
		this.lukija = lukija;
		this.saadot = bricklukija;
		this.soittaja = new Soittaja(60);
		soittaja.asetaInstrumentti(0);
		valikko = new String[4];
		valikko[0] = "Piano";
		valikko[1] = "Huilu";
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
			 nappainkoodi = saadot.annaKoodi();
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
			 		soitintila(0,100);
			 		tekstivalikko.esitaValikko();
			 		break;
			 	case 1:
			 		tekstivalikko.tyhjennaNaytto();
			 		soitintila(1,200);
			 		tekstivalikko.esitaValikko();
			 		break;
			 	case 2:
			 		tekstivalikko.tyhjennaNaytto();
			 		soitintila(2,10);
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
		LCD.drawString("Palataan...", 0, 0);
		Delay.msDelay(1000);
		
	}
	
	public void soitintila(int soitinvalinta, int kesto){
		
		int komento = 0;
		int saatoKomento = 0;
		boolean lopeta = false;
		soittaja.asetaInstrumentti(soitinvalinta);
		soittaja.asetaKesto(kesto);
		
		
		do{
			
			komento = lukija.annaKomento();
			saatoKomento = saadot.annaKoodi();
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
		
			case Button.ID_LEFT:
				soittaja.muutaAanenVoimakkuutta(-10);
				break;
			case Button.ID_RIGHT:
				soittaja.muutaAanenVoimakkuutta(10);
				break;
			case Button.ID_ESCAPE:
				lopeta = true;
				break;
			}
		
		} while (!lopeta);
	}
		

}
