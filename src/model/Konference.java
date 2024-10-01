package model;

import java.time.LocalDate;

import java.util.ArrayList;

public class Konference {

	private String navn; 
	private String sted;
	private LocalDate start;
	private LocalDate slut;
	private double pris;

	// Dobbeltrettet 0..1-til -mange komposition
	private final ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
	// Dobbeltrettet 0..* komposition: --> Udflugt
	private final ArrayList<Udflugt> udflugter = new ArrayList<>();

	private final ArrayList<Hotel> hoteller = new ArrayList<>();

	public Konference(String navn, String sted, double pris, LocalDate start, LocalDate slut) {
		this.navn = navn;
		this.sted = sted;
		this.pris = pris;
		this.start = start;
		this.slut = slut;

	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getSted() {
		return sted;
	}

	public void setSted(String sted) {
		this.sted = sted;
	}

	public LocalDate getStartDato() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getSlutDato() {
		return slut;
	}

	public void setSlut(LocalDate slut) {
		this.slut = slut;
	}

	public double getPris() {
		return pris;
	}

	public void setPris(double pris) {
		this.pris = pris;
	}

	public ArrayList<Tilmelding> getTilmeldinger() {
		return new ArrayList<>(tilmeldinger);
	}

	public Tilmelding createTilmelding(Deltager deltager, boolean foredragsholder, LocalDate amkomstDato,
			LocalDate afrejseDato) {
		Tilmelding tilmelding = new Tilmelding(deltager, foredragsholder, amkomstDato, afrejseDato, this);
		tilmeldinger.add(tilmelding);
		return tilmelding;
	}

	public void removeTilmelding(Tilmelding tilmelding) {
		if (tilmeldinger.contains(tilmelding)) {
			tilmeldinger.remove(tilmelding);
		}
	}

	public ArrayList<Udflugt> getUdflugter() {
		return new ArrayList<>(udflugter);
	}

	public Udflugt createUdflugt(String navn, String sted, double pris, LocalDate startDato, LocalDate slutDato) {
		Udflugt udflugt = new Udflugt(navn, sted, pris, startDato, slutDato);
		udflugter.add(udflugt);
		return udflugt;
	}

	public void removeUdflugt(Udflugt Udflugt) {
		if (udflugter.contains(Udflugt)) {
			udflugter.remove(Udflugt);
		}
	}

	public void addHotel(Hotel hotel) {
		hoteller.add(hotel);

	}

	public void removeHotel(Hotel hotel) {
		if (hoteller.contains(hotel)) {
			hoteller.remove(hotel);
		}

	}

	public ArrayList<Hotel> getHoteller() {
		return new ArrayList<>(hoteller);
	}

	@Override
	public String toString() {
		return navn;
	}
	
}
