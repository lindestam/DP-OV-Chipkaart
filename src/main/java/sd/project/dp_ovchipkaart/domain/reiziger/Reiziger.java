package sd.project.dp_ovchipkaart.domain.reiziger;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import sd.project.dp_ovchipkaart.domain.OV_chipkaart.OV_chipkaart;
import sd.project.dp_ovchipkaart.domain.adres.Adres;

import java.util.List;

public class Reiziger {
    private int reiziger_id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private java.sql.Date geboortedatum;
    private Adres adres;
    private List<OV_chipkaart> ovchipkaart;

    public Reiziger() {
    }

    public Reiziger(int reiziger_id, String vlt, String tvgsel, String anm, java.sql.Date gbtdtm) {
        this.reiziger_id = reiziger_id;
        this.voorletters = vlt;
        this.tussenvoegsel = tvgsel;
        this.achternaam = anm;
        this.geboortedatum = gbtdtm;
    }
    @Id
    public int getId() {
        return reiziger_id;
    }

    public void setId(int id) {
        this.reiziger_id = id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsels() {
        return tussenvoegsel;
    }

    public void setTussenvoegsels(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public java.sql.Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(java.sql.Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String toString() {
        return "Reiziger{" +
                "reiziger_id=" + reiziger_id +
                ", voorletters='" + voorletters + '\'' +
                ", tussenvoegsel='" + tussenvoegsel + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", geboortedatum=" + geboortedatum +
                ", adres=" + adres +  // Voeg adres toe aan de toString
                '}';
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public List<OV_chipkaart> getOvchipkaarten() {
        return ovchipkaart;
    }

    public void setOvchipkaart(List<OV_chipkaart> ovchipkaart) {
        this.ovchipkaart = ovchipkaart;
    }
}
