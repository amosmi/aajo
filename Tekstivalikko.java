package aajobotti;

import lejos.hardware.lcd.LCD;

/**
 * <h1> Tekstivalikko </h1>
 * Tekstivalikko-luokan avulla voi luoda tekstipohjaisia valikoita ev3-brickin n�yt�lle. 
 * Otsikko tulee String muodossa, valittavat kohdat taulukossa annetaan String-taulukkona,
 * taulukko voidaan tulostaa n�yt�lle esitaValikko()-metodia k�ytt�en. String-taulukon 
 * ensimm�inen alkio esitet��n ylimp�n�. Alasp�in siirrytt�ess� kasvatetaan taulukon indeksi�.
 * Alas ja yl�s viittaa n�kym��n brickin lcd-n�yt�ll�. Valittuna olevaa kohtaa seurataan 
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
	 * Tekstivalikossa ensimm�inen valittava kohde on valittuna.
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
	 * T�m�n metodin avulla siirret��n valitsinta yksi eteenp�in String-listalla. Jos valitsin on jo viimeisen
	 * kohteen kohdalla, valitsin hypp�� listan ensimm�iseen kohteeseen. N�ytt� ei p�ivity t�m�n metodin
	 * avulla, vaan se pit�� p�ivitt�� metodia esitaValikko()-avulla.
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
	 * T�m�n metodin avulla siirret��n valitsinta listalla yksi taaksep�in. Jos kyseess� on taulukon ensimmainen
	 * alkio, valitsin hypp�� taulukon ensimmaiseen kohteeseen. 
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
	 * T�m�n metodin avulla esitet��n valikko brickin LCD-n�yt�ll�. 
	 */
	public void esitaValikko(){
		LCD.clear();
		LCD.drawString(otsikko, 0, 0);
		for (int i = 0; i < otsakkeet.length;i++){
			LCD.drawString(otsakkeet[i], 0, i+1,kaanteinen[i]);
		}
	}
	
	/**
	 * Tyhjent� n�yt�n.
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
	
