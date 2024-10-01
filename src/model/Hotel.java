package model;

import java.util.ArrayList;

public class Hotel {

	// composition: --> 0..* Tillæg
	private final ArrayList<Tillæg> tillæg = new ArrayList<>();
	// association: --> 0..* tilmeldinger
	private final ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();

	private String navn;
	private String sted;
	private double prisEnkeltSeng;
	private double prisDobbeltSeng;

	public Hotel(String navn, String sted, double prisEnkeltSeng, double prisDobbeltSeng) {
		this.navn = navn;
		this.sted = sted;
		this.prisEnkeltSeng = prisEnkeltSeng;
		this.prisDobbeltSeng = prisDobbeltSeng;
	}

    public ArrayList<Tilmelding> getTilmeldinger() {
        return new ArrayList<>(tilmeldinger);
    }

    public void addTilmelding(Tilmelding tilmelding) {
    if (!tilmeldinger.contains(tilmelding)) {
        tilmeldinger.add(tilmelding);
        tilmelding.setHotel(this);
    }
    }

    public void removeTilmelding(Tilmelding tilmelding) {
        if (tilmeldinger.contains(tilmelding)) {
            tilmeldinger.remove(tilmelding);
            tilmelding.setHotel(null);
        }
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

	public double getPrisEnkeltSeng() {
		return prisEnkeltSeng;
	}

	public void setPrisEnkeltSeng(double prisEnkeltSeng) {
		this.prisEnkeltSeng = prisEnkeltSeng;
	}

	public double getPrisDobbeltSeng() {
		return prisDobbeltSeng;
	}

	public void setPrisDobbeltSeng(double prisDobbeltSeng) {
		this.prisDobbeltSeng = prisDobbeltSeng;
	}

    public ArrayList<Tillæg> getTillæg() {
        return new ArrayList<>(tillæg);
    }

    public Tillæg createTillæg(String name, double pris) {
        Tillæg tillæg = new Tillæg(name, pris);
        this.tillæg.add(tillæg);
        return tillæg;
    } 

    public void removeTillæg(Tillæg Tillæg) {
        if (this.tillæg.contains(Tillæg)) {
            this.tillæg.remove(Tillæg);
        }
    }
    @Override
    public String toString() {
    	return navn;
    }

}