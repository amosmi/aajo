package musa;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.utility.Delay;

public class Paavalikko {
	private BrickLukija saadot;
	private Tekstivalikko tekstivalikko;
	private String[] valikko;
	private int valitsin;
	private EV3IRSensor irsensori;
	private IRLukija lukija;
	private Soittotila soittotila;
	private Motors moottorit;

	public Paavalikko() {

		this.irsensori = new EV3IRSensor(SensorPort.S1);
		this.lukija = new IRLukija(irsensori);
		this.saadot = new BrickLukija();
		valikko = new String[4];
		valikko[0] = "Ampumatila";
		valikko[1] = "Golffitila";
		valikko[2] = "Soittotila";
		valikko[3] = "Lopeta";
		tekstivalikko = new Tekstivalikko("Valikko", valikko);
		valitsin = tekstivalikko.annaKohta();

	}

	public void valikko() {
		lukija.start();
		saadot.start();
		int nappainkoodi;
		boolean lopeta = false;
		tekstivalikko.esitaValikko();
		do {
			nappainkoodi = saadot.annaKoodi();
			switch (nappainkoodi) {
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
				switch (this.valitsin) {
				case 0:
					tekstivalikko.tyhjennaNaytto();
					this.moottorit.liikkeet(1);
					break;
				case 1:
					tekstivalikko.tyhjennaNaytto();
					this.moottorit.liikkeet(2);
					break;
				case 2:
					tekstivalikko.tyhjennaNaytto();
					this.soittotila.suorita();

					break;
				case 3:
					tekstivalikko.tyhjennaNaytto();
					lopeta = true;
					break;

				}

			}

		} while (!lopeta);
		saadot.lopeta();
		lukija.lopeta();
		LCD.drawString("Suljetaan", 0, 0);
		Delay.msDelay(1337);
		irsensori.close();

	}

}
