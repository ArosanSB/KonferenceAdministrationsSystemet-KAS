package model;

import java.time.LocalDate;

public class MainApp {

	public static void main(String[] args) {

		// 1.
		Konference HavOgHimmel = new Konference("Hav og Himmel", "Odense", 1500, LocalDate.of(2021, 5, 18),
				LocalDate.of(2021, 5, 20));
		Deltager d1 = new Deltager("Finn Madsen", "12 34 56 78", "123 WeedStreet", "Århus, 8200", false);
		Tilmelding t1 = HavOgHimmel.createTilmelding(d1, false, LocalDate.of(2021, 5, 18), LocalDate.of(2021, 5, 20));
		System.out.println(t1.samletPris());
		
		
		// 2.
		Deltager d2 = new Deltager("Niels Petersen", "12 34 56 78", "123 WeedStreet", "Århus, 8200", false);
		Tilmelding t2 = HavOgHimmel.createTilmelding(d2, false, LocalDate.of(2021, 5, 18), LocalDate.of(2021, 5, 20));
		Hotel Svane = new Hotel("Den Hvide Svane", "Odense C", 1050, 1250);
		t2.setHotel(Svane);
		HavOgHimmel.addHotel(Svane);
		System.out.println(t2.samletPris());
		System.out.println(HavOgHimmel.getHoteller());

		
		// 3.
		Deltager d3 = new Deltager("Peter Sommer", "12 34 56 78", "123 WeedStreet", "Århus, 8200", false);
		Tilmelding t3 = HavOgHimmel.createTilmelding(d3, false, LocalDate.of(2021, 5, 18), LocalDate.of(2021, 5, 20));
		Tillæg swifi = Svane.createTillæg("Wifi", 50);
		Ledsager mie = d3.createLedsager("Mie");
		t3.addTillæg(swifi);
		t3.setHotel(Svane);
		Udflugt Egeskov = HavOgHimmel.createUdflugt("Egeskov", "Odense", 75, LocalDate.of(2021, 5, 19),
				LocalDate.of(2021, 5, 19));

		Udflugt TrapHolt = HavOgHimmel.createUdflugt("Trapholt", "Odense", 200, LocalDate.of(2021, 5, 20),
				LocalDate.of(2021, 5, 20));

		mie.addUdflugt(TrapHolt);
		mie.addUdflugt(Egeskov);

		System.out.println(t3.samletPris());
		
		// 4.
		Deltager d4 = new Deltager("Lone Jensen", "12 34 56 78", "123 WeedStreet", "Århus, 8200", false);
		Tilmelding t4 = HavOgHimmel.createTilmelding(d4, true, LocalDate.of(2021, 5, 18), LocalDate.of(2021, 5, 20));
		Ledsager Jan = d4.createLedsager("Jan Madsen");
		t4.setHotel(Svane);
		Jan.addUdflugt(Egeskov);
		Udflugt Byrundtur = HavOgHimmel.createUdflugt("Byrundtur", "Odense", 125, LocalDate.of(2021, 5, 18),
				LocalDate.of(2021, 5, 18));

		Jan.addUdflugt(Byrundtur);
		t4.addTillæg(swifi);
		
		System.out.println(t4.samletPris());
		System.out.println(HavOgHimmel.getUdflugter());
	}

}
