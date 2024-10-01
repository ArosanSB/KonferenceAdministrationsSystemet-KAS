package storage;

import java.util.ArrayList;
import model.Deltager;
import model.Konference;
import model.Hotel;

public class Storage {
	private static ArrayList<Deltager> deltagere = new ArrayList<>();
	private static ArrayList<Hotel> hoteller = new ArrayList<>();
	private static ArrayList<Konference> konferencer = new ArrayList<>();

	// -------------------------------------------------------------------------

	public static ArrayList<Deltager> getDeltager() {
		return new ArrayList<>(deltagere);
	}

	public static void addDeltager(Deltager deltager) {
		deltagere.add(deltager);
	}

	public static void removeDeltager(Deltager deltager) {
		deltagere.remove(deltager);
	}

	// -------------------------------------------------------------------------

	public static ArrayList<Konference> getKonferencer() {
		return new ArrayList<>(konferencer);
	}

	public static void addKonference(Konference konference) {
		konferencer.add(konference);
	}

	public static void removeKonference(Konference konference) {
		konferencer.remove(konference);
	}

	// -------------------------------------------------------------------------
	public static ArrayList<Hotel> getHoteller() {
		return new ArrayList<>(hoteller);
	}

	public static void addHotel(Hotel hotel) {
		hoteller.add(hotel);
	}

	public static void removeHotel(Hotel hotel) {
		hoteller.remove(hotel);
	}
}
