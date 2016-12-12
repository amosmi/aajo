package aajobotti;

import lejos.hardware.Sound;

public class Soittaja {
	
	private int[] instrumentti = new int[5];
	private int aanenVoimakkuus;
	private int kesto;
	private final static int MAXVOL = 100;
	
	public Soittaja(int aanenVoimakkuus){
		
		if (aanenVoimakkuus < 0 || aanenVoimakkuus > MAXVOL){
			this.aanenVoimakkuus = 50;
		}else{
			this.aanenVoimakkuus = aanenVoimakkuus;
		}
		this.kesto = 100;
				
	}
	
	
	public void asetaInstrumentti(int valinta){
		if (valinta == 0){
			asetaTaulukonArvot(20, 25, 500, 7000, 100);
		}else if (valinta == 1){
			asetaTaulukonArvot(200, 20, 5000, 5000, 1000);
		}else if (valinta == 2){
			asetaTaulukonArvot(1, 2, 100, 100, 5);
		}
	}
	
	public void asetaKesto(int kesto){
		this.kesto = kesto;
	}
	
	private void asetaTaulukonArvot(int i0, int i1, int i2, int i3, int i4){
		this.instrumentti[0] = i0;
		this.instrumentti[1] = i1;
		this.instrumentti[2] = i2;
		this.instrumentti[3] = i3;
		this.instrumentti[4] = i4;
	}
	
	public void muutaAanenVoimakkuutta (int muutos){
		
		if (this.aanenVoimakkuus + muutos < 0){
			this.aanenVoimakkuus = 0;
		}else if(this.aanenVoimakkuus + muutos > MAXVOL){
			this.aanenVoimakkuus = MAXVOL;
		}else{
			this.aanenVoimakkuus += muutos;
		}
		Sound.setVolume(aanenVoimakkuus);
	}
	
	public void soita(int taajuus){
		Sound.playNote(instrumentti, taajuus, this.kesto);
	}
	
	public int annaAanenVoimakkuus(){
		return this.aanenVoimakkuus;
	}

}
