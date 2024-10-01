package model;

import java.util.ArrayList;

public class Ledsager {

//Associering: --> 0..* Udflugt
	private ArrayList<Udflugt> udflugter = new ArrayList<>();
	private String navn;

	Ledsager(String navn) {
		this.navn = navn;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public ArrayList<Udflugt> getUdflugter() {
		return new ArrayList<>(udflugter);
	}

	public void addUdflugt(Udflugt udflugt) {
		udflugter.add(udflugt);

	}

	public void removeUdflugt(Udflugt udflugt) {
		if (udflugter.contains(udflugt)) {
			udflugter.remove(udflugt);
		}
	}
	
	
	public double udflugtsPris() {
		double pris = 0;
		for (Udflugt udflugt: udflugter) {
			pris += udflugt.getPris();
		}
		
		return pris;
	}
	

}