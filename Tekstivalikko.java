package aajobotti;

import lejos.hardware.lcd.LCD;

/**
 * <h1> Tekstivalikko </h1>
 * Tekstivalikko-luokan avulla voi luoda tekstipohjaisia valikoita ev3-brickin näytölle. 
 * Otsikko tulee String muodossa, valittavat kohdat taulukossa annetaan String-taulukkona,
 * taulukko voidaan tulostaa näytölle esitaValikko()-metodia käyttäen. String-taulukon 
 * ensimmäinen alkio esitetään ylimpänä. Alaspäin siirryttäessä kasvatetaan taulukon indeksiä.
 * Alas ja ylös viittaa näkymään brickin lcd-näytöllä. Valittuna olevaa kohtaa seurataan 
 * int-muuttujan avulla. 
 * 
 * @author JuhaVuokko
 *
 */

public class Tekstivalikko {
	
	private String otsikko;
	private String[] otsakkeet;
	private boolean[] kaanteinen;
	private int kohta;
	
	/**
	 * Tekstivalikossa ensimmäinen valittava kohde on valittuna.
	 * @param otsikko String Tekstivalikkoon tuleva otsikko.
	 * @param otsakkeet String[] Valittavissa olevat kohteet.
	 */
	
	public Tekstivalikko(String otsikko, String[] otsakkeet){
		this.kohta = 0;
		this.otsikko = otsikko;
		this.otsakkeet = otsakkeet;
		this.kaanteinen = new boolean[otsakkeet.length];
		this.kaanteinen[0] = true;
		for (int i = 1; i < kaanteinen.length; i++){
			this.kaanteinen[i] = false;
		} 
	}
	
	/**
	 * Tämän metodin avulla siirretään valitsinta yksi eteenpäin String-listalla. Jos valitsin on jo viimeisen
	 * kohteen kohdalla, valitsin hyppää listan ensimmäiseen kohteeseen. Näyttö ei päivity tämän metodin
	 * avulla, vaan se pitää päivittää metodia esitaValikko()-avulla.
	 * 
	 */
	public void alas(){
		if (this.kohta< otsakkeet.length-1){
			this.kohta++;
		}else{
			this.kohta = 0;
		}
		for (int i = 0; i < kaanteinen.length; i++){
			kaanteinen[i] = false;
		}
		kaanteinen[this.kohta] = true;
		
	}
	
	/**
	 * Tämän metodin avulla siirretään valitsinta listalla yksi taaksepäin. Jos kyseessä on taulukon ensimmainen
	 * alkio, valitsin hyppää taulukon ensimmaiseen kohteeseen. 
	 */
	public void ylos(){
		if (this.kohta>0){
			this.kohta--;
		}else{
			this.kohta = kaanteinen.length-1;
		}
		for (int i = 0; i < kaanteinen.length; i++){
			kaanteinen[i] = false;
		}
		kaanteinen[kohta] = true;
	}
	
	/**
	 * Tämän metodin avulla esitetään valikko brickin LCD-näytöllä. 
	 */
	public void esitaValikko(){
		LCD.clear();
		LCD.drawString(otsikko, 0, 0);
		for (int i = 0; i < otsakkeet.length;i++){
			LCD.drawString(otsakkeet[i], 0, i+1,kaanteinen[i]);
		}
	}
	
	/**
	 * Tyhjentä näytön.
	 */
	public void tyhjennaNaytto(){
		LCD.clear();
	}
	
	/**
	 * Palauttaa valittuna olevan taulukon otsakkeen kohdan.
	 * @return int Valittuna olevan otsakkeen kohta taulukossa.
	 */
	public int annaKohta(){
		return this.kohta;
	}

}
	
