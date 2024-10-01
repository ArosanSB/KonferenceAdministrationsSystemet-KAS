package model;

import java.time.LocalDate;

import java.util.ArrayList;
import java.time.temporal.ChronoUnit;

public class Tilmelding {

	private LocalDate ankomstDato;
	private LocalDate afrejseDato;
	private boolean isForedragsholder;
	private Deltager deltager;
	private ArrayList<Tillæg> tillæg = new ArrayList<>();
	private Hotel hotel;
	private Konference konference;

	// Package visibility
	Tilmelding(Deltager deltager, boolean foredragsholder, LocalDate ankomstDato, LocalDate afrejseDato,
			Konference konference) {
		this.deltager = deltager;
		this.afrejseDato = afrejseDato;
		this.ankomstDato = ankomstDato;
		this.isForedragsholder = foredragsholder;
		this.deltager = deltager;
		this.konference = konference;

	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		if (this.hotel != hotel) {
			if (this.hotel != null) {
				hotel.removeTilmelding(this);
			}
			this.hotel = hotel;
			if (hotel != null) {
				hotel.addTilmelding(this);
			}
		}
	}

	public ArrayList<Tillæg> getTillæg() {
		return tillæg;
	}

	public void addTillæg(Tillæg tillæg) {
		this.tillæg.add(tillæg);
	}

	public void removeTillæg(Tillæg tillæg) {
		if (this.tillæg.contains(tillæg)) {
			this.tillæg.remove(tillæg);
		}
	}

	public LocalDate getAnkomstDato() {
		return ankomstDato;
	}

	public void setAnkomstDato(LocalDate ankomstDato) {
		this.ankomstDato = ankomstDato;
	}

	public LocalDate getAfrejseDato() {
		return afrejseDato;
	}

	public void setAfrejseDato(LocalDate afrejseDato) {
		this.afrejseDato = afrejseDato;
	}

	public boolean isForedragsholder() {
		return isForedragsholder;
	}

	public void setForedragsholder(boolean isForedragsholder) {
		this.isForedragsholder = isForedragsholder;
	}

	public Deltager getDeltager() {
		return deltager;
	}

	public void setDeltager(Deltager deltager) {
		if (this.deltager != deltager) {
			this.deltager = deltager;
		}
	}

	public double samletPris() {
		double samletPris = 0;
		if (deltager.getLedsager() != null) {
			samletPris += deltager.getLedsager().udflugtsPris();
		}
		if (isForedragsholder == false) {
			samletPris += konference.getPris() * (ChronoUnit.DAYS.between(ankomstDato, afrejseDato) + 1);
		}
		
		
		if (hotel != null) {
			if (deltager.getLedsager() == null) {
				samletPris += hotel.getPrisEnkeltSeng() * ChronoUnit.DAYS.between(ankomstDato, afrejseDato);

		} else {
			samletPris += hotel.getPrisDobbeltSeng() * ChronoUnit.DAYS.between(ankomstDato, afrejseDato);

		}
		}
		for (Tillæg tillæg : this.tillæg) {
			samletPris += tillæg.getPris() * ChronoUnit.DAYS.between(ankomstDato, afrejseDato);
		}

		return samletPris;
	}

	@Override
	public String toString() {
		String results = "";
		if (isForedragsholder) {
			results = deltager.getNavn() + " (Foredragsholder)";
		} else if (deltager.isErAdministrator()){
			results = deltager.getNavn() + " (Administrator)";
		}
		else {
			results = deltager.getNavn();
		}
		return results;
	}

}