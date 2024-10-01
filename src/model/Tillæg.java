package model;

public class Tillæg {

	private String navn;
	private double pris;

	public Tillæg(String navn, double pris) {
		this.navn = navn;
		this.pris = pris;

	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public double getPris() {
		return pris;
	}

	public void setPris(double pris) {
		this.pris = pris;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(navn);
		sb.append(" " + pris);
		return sb.toString();
	}

}