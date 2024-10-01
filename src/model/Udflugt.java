package model;

import java.time.LocalDate;


public class Udflugt {

	private String navn;
	private String sted;
	private String beskrivelse;
	private LocalDate startDato;
	private LocalDate slutDato;
	private double pris;

	Udflugt(String navn, String sted, double pris, LocalDate startDato, LocalDate slutDato) {
		this.navn = navn;
		this.sted = sted;
		this.startDato = startDato;
		this.slutDato = slutDato;
		this.pris = pris;
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

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

	public LocalDate getStartDato() {
		return startDato;
	}

	public void setStartDato(LocalDate startDato) {
		this.startDato = startDato;
	}

	public LocalDate getSlutDato() {
		return slutDato;
	}

	public void setSlutDato(LocalDate slutDato) {
		this.slutDato = slutDato;
	}

	public double getPris() {
		return pris;
	}

	public void setPris(double pris) {
			this.pris = pris;
	}

	@Override
	public String toString() {
		return navn;
	}


}