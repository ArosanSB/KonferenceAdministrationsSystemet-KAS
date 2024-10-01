package model;

public class Deltager {

	private String navn;
	private String telefonNr;
	private String addresse;
	private String firma;
	private String by;
	private boolean erAdministrator;
    private Ledsager ledsager;
	
	public Deltager(String navn, String telefonNr, String addresse, String by, boolean erAdministrator) {
		this.navn = navn;
		this.telefonNr = telefonNr;
		this.addresse = addresse;
		this.by = by;
		this.erAdministrator = erAdministrator;
	}

    public Ledsager createLedsager(String navn) {
      Ledsager ledsager = new Ledsager(navn);
      this.ledsager=ledsager;
      return ledsager;
    }

    public void removeLedsager() {
        if (ledsager!=null) {
            ledsager=null;
        }
    }

    public Ledsager getLedsager() {
        return ledsager;
    }

	public void setNavn(String navn) {
		this.navn = navn;
	}
	
	public String getNavn() {
		return navn;
	}

	public String getTelefonNr() {
		return telefonNr;
	}

	public void setTelefonNr(String telefonNr) {
		this.telefonNr = telefonNr;
	}

	public String getAddresse() {
		return addresse;
	}

	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public boolean isErAdministrator() {
		return erAdministrator;
	}

	public void setErAdministrator(boolean erAdministrator) {
		this.erAdministrator = erAdministrator;
	}

}