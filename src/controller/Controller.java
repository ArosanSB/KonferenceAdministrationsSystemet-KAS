package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import model.Deltager;
import model.Konference;
import model.Hotel;
import storage.Storage;
import model.Ledsager;
import model.Udflugt;
import model.Tilmelding;
import model.Tillæg;

public class Controller {
	// -------------------------------------------------------------------------
	// Konference
	// -------------------------------------------------------------------------
	public static Konference createKonference(String name, String adresse, double pris, LocalDate start,
			LocalDate slut) {
		Konference konference = new Konference(name, adresse, pris, start, slut);
		Storage.addKonference(konference);
		;
		return konference;
	}

	public static void deleteKonference(Konference konference) {
		Storage.removeKonference(konference);
	}

	public static void updateKonference(Konference konference, String name, String adresse, double pris,
			LocalDate start, LocalDate slut) {
		konference.setNavn(name);
		konference.setSted(adresse);
		konference.setStart(start);
		konference.setSlut(slut);
		konference.setPris(pris);
	}

	public static ArrayList<Konference> getKonferencer() {
		return Storage.getKonferencer();
	}

	// -------------------------------------------------------------------------
	// Hotel
	// -------------------------------------------------------------------------

	public static Hotel createHotel(String navn, String sted, double prisEnkeltSeng, double prisDobbeltSeng) {
		Hotel hotel = new Hotel(navn, sted, prisEnkeltSeng, prisDobbeltSeng);
		Storage.addHotel(hotel);
		return hotel;
	}

	public static void deleteHotel(Hotel hotel) {
		Storage.removeHotel(hotel);
	}

	public static void updateHotel(Hotel hotel, String name, String adresse, double prisEnkelt, double prisDobbelt) {
		hotel.setNavn(name);
		hotel.setPrisDobbeltSeng(prisDobbelt);
		hotel.setPrisEnkeltSeng(prisEnkelt);
		hotel.setSted(adresse);
	}

    public static void addHotelTilKonference(Hotel hotel, Konference konference) {
        konference.addHotel(hotel);
    }

    public static void removeHotelFraKonfernce(Hotel hotel, Konference konference) {
        konference.removeHotel(hotel);
    }

    	
	public static ArrayList<Hotel> getHoteller() {
		return Storage.getHoteller();
	}

	// -------------------------------------------------------------------------
	// Deltager
	// -------------------------------------------------------------------------

	public static Deltager createDeltager(String navn, String telefonNr, String addresse, String by,
			boolean erAdministrator) {
		Deltager deltager = new Deltager(navn, telefonNr, addresse, by, erAdministrator);
		Storage.addDeltager(deltager);
		return deltager;
	}

	public static void deleteDeltager(Deltager deltager) {
		Storage.removeDeltager(deltager);
	}

	public static void updateDeltager(Deltager deltager, String navn, String telefonNr, String addresse, String by,
			boolean erAdministrator) {
		deltager.setNavn(navn);
		deltager.setTelefonNr(telefonNr);
		deltager.setAddresse(addresse);
		deltager.setBy(by);
		deltager.setErAdministrator(erAdministrator);
	}

	public static ArrayList<Deltager> getDeltager() {
		return Storage.getDeltager();
	}
	// -------------------------------------------------------------------------
	// Ledsager
	// -------------------------------------------------------------------------

	public static Ledsager createLedsager(Deltager deltager, String navn) {
		Ledsager ledsager = deltager.createLedsager(navn);
		return ledsager;
	}

	// -------------------------------------------------------------------------
	// Tilmelding
	// -------------------------------------------------------------------------

	public static Tilmelding createTilmelding(Konference konference, Deltager deltager, boolean foredragsholder, LocalDate ankomstDato, LocalDate afrejseDato) {
    Tilmelding tilmelding = konference.createTilmelding(deltager, foredragsholder, ankomstDato, afrejseDato);
    return tilmelding;
	}

    public static void addHotelTilTilmelding(Tilmelding tilmelding, Hotel hotel) {
        tilmelding.setHotel(hotel);
    }

    public static void removeHotelFraTilmelding(Tilmelding tilmelding) {
        tilmelding.setHotel(null);
    }


	// -------------------------------------------------------------------------
	// Udflugt
	// -------------------------------------------------------------------------

	public static Udflugt createUdflugt(Konference konference, String navn, String sted, double pris,
			LocalDate startDato, LocalDate slutDato) {
		Udflugt udflugt = konference.createUdflugt(navn, sted, pris, startDato, slutDato);
		return udflugt;
	}

	public static void removeUdflugt(Konference konference, Udflugt udflugt) {
		konference.removeUdflugt(udflugt);	
	}
	
	public static void updateUdflugt(Udflugt Udflugt, String name, String adresse, double pris,
			LocalDate start, LocalDate slut) {
		Udflugt.setNavn(name);
		Udflugt.setSted(adresse);
		Udflugt.setStartDato(start);
		Udflugt.setPris(pris);
		Udflugt.setSlutDato(slut);
	}

	public static void addLedsagerToUdflugt(Ledsager ledsager, Udflugt udflugt) {
		ledsager.addUdflugt(udflugt);
	}

	public static void removeLedsagerToUdflugt(Ledsager ledsager, Udflugt udflugt) {
		ledsager.removeUdflugt(udflugt);
	}

	public static Ledsager getLedsagerUdflugter() {
		return getLedsagerUdflugter();
	}

	// -------------------------------------------------------------------------
	// Tillæg
	// -------------------------------------------------------------------------

	public static Tillæg createTillæg(Hotel hotel, String navn, double pris) {
		Tillæg tillæg = hotel.createTillæg(navn, pris);
		return tillæg;
	}

    public static void addTillægTilTilmelding(Tillæg tillæg, Tilmelding tilmelding) {
        tilmelding.addTillæg(tillæg);
    }

    public static void removeTillægFraTilmelding(Tillæg tillæg, Tilmelding tilmelding) {
        tilmelding.removeTillæg(tillæg);
    }


	// -------------------------------------------------------------------------

	/**
	 * Initializes the storage with some objects.
	 */
	public static void initStorage() {
		Konference k1 = Controller.createKonference("Hav og Himmel", "Odense", 2500, LocalDate.of(2021, 5, 19), LocalDate.of(2021, 5, 22));
		Deltager d1 = Controller.createDeltager("James", "23 37 28 39", "Test 123 123 ", "Odense", false);
		Deltager d2 = Controller.createDeltager("Travis Scott", "12 34 56 78", "Et eller andet sted i USA", "America", false);
		Tilmelding t1 = Controller.createTilmelding(k1, d2, true, LocalDate.of(2021, 5, 19), LocalDate.of(2021, 5, 22));
		Controller.createTilmelding(k1, d1, false, LocalDate.of(2021, 5, 19), LocalDate.of(2021, 5, 22));
		
		Hotel h1 = Controller.createHotel("Hvid Svane", "Odense", 950, 1250);
		Controller.addHotelTilKonference(h1, k1);
		d2.createLedsager("Mie");
		
		Controller.createUdflugt(k1, "Stranden", "Odense", 200, LocalDate.of(2021, 5, 19), LocalDate.of(2021, 5, 22));
		t1.setHotel(h1);
		
	}

	public static void init() {

		initStorage();

	}

}
