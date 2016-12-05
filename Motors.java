package musa;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RegulatedMotor;

public class Motors {
	private RegulatedMotor right;
	private RegulatedMotor left;
	private RegulatedMotor middle;
	private IRLukija lukija;

	public Motors() {
		this.right = new EV3LargeRegulatedMotor(MotorPort.D);
		this.left = new EV3LargeRegulatedMotor(MotorPort.B);
		this.middle = new EV3MediumRegulatedMotor(MotorPort.A);

	}

	public void liikkeet(int valinta) {

		while (!Button.ESCAPE.isUp()) {
			switch (lukija.annaKomento()) {
			case 5:
				this.eteenpain();
				LCD.clear();
				LCD.drawString("Eteenpain", 0, 0);
				break;
			case 8:
				this.taaksepain();
				LCD.clear();
				LCD.drawString("Taaksepain", 0, 0);
				break;
			case 2:
				this.seis();
				LCD.clear();
				LCD.drawString("SEIS", 0, 0);
				break;
			case 4:
				if (valinta == 1) {
					this.golf();
				} else if (valinta == 2) {
					this.ammuKuula();
				}
				break;
			case 1:
				this.vasemmalle();
				LCD.clear();
				LCD.drawString("Kaantyy vasemmalle", 0, 0);
				break;
			case 3:
				this.oikealle();
				LCD.clear();
				LCD.drawString("Kaantyy oikealle", 0, 0);
				break;
			}
			this.sammuta();

		}
	}

	public void taaksepain() {

		this.left.forward();
		this.right.forward();
	}

	public void eteenpain() {

		this.left.backward(); // moottorien oma suunta on taaksepain
		this.right.backward();
	}

	public void oikealle() {
		this.left.forward();
		this.right.backward();
	}

	public void vasemmalle() {
		this.right.forward();
		this.left.backward();
	}

	public void seis() {
		this.left.stop(true);
		this.right.stop(true);
	}

	public void golf() {
		this.middle.setSpeed(500);
		this.middle.rotate(-90);
		this.middle.setSpeed(1500);
		this.middle.rotate(135);
		this.middle.setSpeed(500);
		this.middle.rotate(-45);
	}

	public void ammuKuula() {
		this.middle.setSpeed(1500);
		this.middle.rotate(270);
		this.middle.rotate(-360);
		this.middle.rotate(90);

	}

	public void sammuta() {
		this.left.stop();
		this.right.stop();
		this.middle.stop();
		this.left.close();
		this.right.close();
		this.middle.close();

	}

}
