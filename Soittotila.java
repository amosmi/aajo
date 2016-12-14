package aajobotti;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.utility.Delay;
import lejos.utility.TextMenu;

/**
 * Soittotila vastaa Aajobotin soittamistilasta. T‰ss‰ tilassa voi soittaa kolmella eri soittimella, pianolla, viululla ja xylofonilla. Ensin valitaan soitin
 * brickin n‰pp‰imien avulla valikosta, valittavana on myˆs paluu p‰‰valikkoon. Soittimen valinnan j‰lkeen p‰‰see soittamaan.Soittaminen tapahtuu 
 * kaukos‰‰timen avulla. Soitaessa- on k‰ytˆss‰ kymmenen eri n‰pp‰inyhdistelm‰‰, jotka tuottavat kymmenen eritaajuista ‰‰nt‰. Paluu soitinvalintaan tapahtuu
 * escape-n‰pp‰imell‰.
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
	private ValoS valoshow;
	private int valitsin;
	
	/**
	 * Soittotila-konstruktorissa alustetaan sis‰iset muuttujat. Luotaessa soittotila-oliota mit‰‰n ei viel‰ esitet‰ brickin n‰ytˆll‰. Soittotila-
	 * oliota hyˆdynnet‰‰n suorita-metodin avulla. Bricklukija- ja IRLukija- oliot pit‰‰ luoda ja k‰ynnist‰‰ soittotila-olion ulkopuolella ja
	 * vastaavasti n‰m‰ pit‰‰ myˆs pys‰ytt‰‰ soittotilan ulkopuolella lopuksi.
	 * @param lukija IRLukija Kaukos‰‰timen n‰pp‰inten painallusten lukemiseksi.
	 * @param bricklukija Bricklukija Brickin n‰pp‰inten painallusten lukemiseksi.
	 */
	public Soittotila(IRLukija lukija, BrickLukija bricklukija){
	
		this.lukija = lukija;
		this.bricklukija = bricklukija;
		this.soittaja = new Soittaja(60);
		soittaja.asetaInstrumentti(0);
		valoshow = new ValoS();
		valikko = new String[4];
		valikko[0] = "Piano";
		valikko[1] = "Viulu";
		valikko[2] = "Xylofoni";
		valikko[3] = "Palaa";
		tekstivalikko = new Tekstivalikko("Soittaja",valikko);
		valitsin =tekstivalikko.annaKohta();
		
		
		
	}
	
	/**
	 * T‰m‰ metodi hoitaa soittotilan. T‰m‰ aikaansaa valikon esitt‰misen brickissa. Kaikki toiminnallisuudet 
	 * k‰ynnistyv‰t myˆs. T‰m‰ metodi k‰ytt‰‰ soitintila-metodia apumetodina. 
	 */
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
		 
		valoshow.loppuvilkutus();
		
		
	}
	
	public void soitintila(int soitinvalinta, int kesto, String soitin){
		
		int komento = 0;
		int edellinenKomento = 0;
		int saatoKomento = 0;
		boolean lopeta = false;
		soittaja.asetaInstrumentti(soitinvalinta);
		this.paivitaNaytto(soitin, soittaja.annaAanenVoimakkuus());
		
		do{
			
			komento = lukija.annaKomento();
			saatoKomento = bricklukija.annaKoodi();
			switch (komento){
			case 1:
				valoshow.vilkuta(1);
				soittaja.soita(200);
				break;
			case 2:
				valoshow.vilkuta(2);
				soittaja.soita(400);
				break;
			case 3:
				valoshow.vilkuta(3);
				soittaja.soita(600);
				break;
			case 4:
				valoshow.vilkuta(4);
				soittaja.soita(800);
				break;
			case 5:
				valoshow.vilkuta(5);
				soittaja.soita(1000);
				break;
			case 6:
				valoshow.vilkuta(6);
				soittaja.soita(1200);
				break;
			case 7:
				valoshow.vilkuta(7);
				soittaja.soita(1400);
				break;
			case 8:
				valoshow.vilkuta(8);
				soittaja.soita(1600);
				break;
			case 10:
				valoshow.vilkuta(9);
				soittaja.soita(1800);
				break;
			case 11:
				valoshow.vilkuta(1);
				soittaja.soita(2000);
				break;
			
			}
			if(edellinenKomento != komento || komento == 0){
				valoshow.lopetaVilkutus();
			}
			
			edellinenKomento = komento;
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
				Delay.msDelay(1000);
				break;
			}
		
		} while (!lopeta);
	}
	
	public void paivitaNaytto(String soitin, int aanenvoimakkuus){
		LCD.drawString(soitin, 0, 0);
		LCD.drawString("Vol: "+ aanenvoimakkuus, 0, 1);
	}
		

}
