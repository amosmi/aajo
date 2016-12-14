package aajobotti;

import lejos.hardware.Sound;
/**
 * <h1> Soittaja </h1>
 * Soittaja-luokan avulla hoidetaan ‰‰nien tuottaminen soittaessa Aajobotin ollessa soittotilassa. 
 * @author JuhaVuokko
 *
 */
public class Soittaja {
	
	private int[] instrumentti = new int[5];
	private int aanenVoimakkuus;
	private int kesto;
	private final static int MAXVOL = 100;
	
	/**
	 * Soittajan konstruktorissa ei viel‰ aseteta soitinta, se t‰ytyy tehd‰ erikseen. T‰ss‰ vaiheessa asetetaan vain alkuarvot ‰‰nen voimakkuudelle ja
	 * kestolle.
	 * @param aanenVoimakkuus int, sallitut arvot ovat [0,100], n‰iden ulkopuolelle menev‰t arvot muutetaan joko maksimi tai minimiarvoksi.
	 */
	public Soittaja(int aanenVoimakkuus){
		
		if (aanenVoimakkuus < 0 || aanenVoimakkuus > MAXVOL){
			this.aanenVoimakkuus = 50;
		}else{
			this.aanenVoimakkuus = aanenVoimakkuus;
		}
		this.kesto = 100;
				
	}
	
	/**
	 * T‰ll‰ metodilla asetetaan soitin. Vaihtoehtoja on kolme.
	 * @param valinta int Valinta voi olla joko 0,1,2 jotka vastaavat soittimia piano, viulu ja xylofoni
	 */
	public void asetaInstrumentti(int valinta){
		if (valinta == 0){
			asetaTaulukonArvot(20, 25, 500, 7000, 100);
		}else if (valinta == 1){
			asetaTaulukonArvot(200, 20, 5000, 5000, 1000);
		}else if (valinta == 2){
			asetaTaulukonArvot(1, 2, 100, 100, 5);
		}
	}
	
	/**
	 * Asetetaan instrumentin soinnin kesto.
	 * @param kesto int soinnin kesto
	 */
	public void asetaKesto(int kesto){
		this.kesto = kesto;
	}
	
	/**
	 * Asetetaan instrumentin m‰‰rittelyyn tarvittavat int-taulukon arvot.
	 * @param i0 int
	 * @param i1 int
	 * @param i2 int 
	 * @param i3 int
	 * @param i4 int
	 */
	private void asetaTaulukonArvot(int i0, int i1, int i2, int i3, int i4){
		this.instrumentti[0] = i0;
		this.instrumentti[1] = i1;
		this.instrumentti[2] = i2;
		this.instrumentti[3] = i3;
		this.instrumentti[4] = i4;
	}
	
	/**
	 * Metodi aanen voimakkuuden muuttamiseen. Aanen voimakkuus pysyy aina minimin ja maksimin v‰liss‰. Jos nykyinen arvo + lis‰ys menee
	 * rajojen ulkopuolelle, asetetaan aanen voimakkuuden arvoksi joko maksimi tai minimi.
	 * @param muutos int Muutos aanen voimakkuutta.
	 */
	public void muutaAanenVoimakkuutta (int muutos){
		
		if (this.aanenVoimakkuus + muutos < 0){
			this.aanenVoimakkuus = 0;
		}else if(this.aanenVoimakkuus + muutos > MAXVOL){
			this.aanenVoimakkuus = MAXVOL;
		}else{
			this.aanenVoimakkuus += muutos;
		}
		Sound.setVolume(this.aanenVoimakkuus);
	}
	
	/**
	 * Huolehtii soittamisesta. Instrumentti ja kesto on jo asetettu muualla, t‰ss‰ voi s‰‰t‰‰ vain 
	 * 
	 * @param taajuus int Asettaa soitettavan soinnun taajuuden.
	 */
	public void soita(int taajuus){
		Sound.playNote(this.instrumentti, taajuus, this.kesto);
	}
	
	/**
	 * Antaa aanen voimakkuuden.
	 * @return int Aanen voimakkuus.
	 */
	public int annaAanenVoimakkuus(){
		return this.aanenVoimakkuus;
	}

}
