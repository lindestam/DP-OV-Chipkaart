package hibernate_ovchipkaart.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "ov_chipkaart")
public class OV_Chipkaart {

    @Id
    @GeneratedValue
    @Column(name = "kaart_nummer")
    private int kaart_nummer;

    @Column(name = "geldig_tot")
    private Date geldig_tot;

    @Column(name = "klasse")
    private int klasse;

    @Column(name = "saldo")
    private double saldo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reiziger_id")
    private ReizigerH reiziger;

    public OV_Chipkaart() {}

    // Constructor
    public OV_Chipkaart(int kaartNummer, Date geldigTot, int klasse, double saldo, ReizigerH reiziger) {
        this.kaart_nummer = kaartNummer;
        this.geldig_tot = geldigTot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;
    }

    // Getters en Setters

    public int getKaart_nummer() {
        return kaart_nummer;
    }

    public void setKaart_nummer(int kaart_nummer) {
        this.kaart_nummer = kaart_nummer;
    }

    public Date getGeldig_tot() {
        return geldig_tot;
    }

    public void setGeldig_tot(Date geldig_tot) {
        this.geldig_tot = geldig_tot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public ReizigerH getReiziger() {
        return reiziger;
    }

    public void setReiziger(ReizigerH reiziger) {
        this.reiziger = reiziger;
    }

    @Override
    public String toString() {
        return "OV_chipkaart{" +
                "kaart_nummer=" + kaart_nummer +
                ", geldig_tot=" + geldig_tot +
                ", klasse=" + klasse +
                ", saldo=" + saldo +
                '}';
    }
}

