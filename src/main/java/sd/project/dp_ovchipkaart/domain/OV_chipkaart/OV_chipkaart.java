package sd.project.dp_ovchipkaart.domain.OV_chipkaart;

import sd.project.dp_ovchipkaart.domain.product.Product;
import sd.project.dp_ovchipkaart.domain.reiziger.Reiziger;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OV_chipkaart {
    private int kaart_nummer;
    private Date geldig_tot;
    private int klasse;
    private int saldo;
    private Reiziger reiziger;
    private List<Product> producten;

    public OV_chipkaart(int kaartNummer, Date geldigTot, int klasse, int saldo, Reiziger reiziger) {
        kaart_nummer = kaartNummer;
        geldig_tot = geldigTot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;
        producten = new ArrayList<>();
    }
    public OV_chipkaart() {}

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

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }
    public List<Product> getProducten() {
        return producten;
    }
    public void voegProductToe(Product product) {
        if (!this.producten.contains(product)) {
            this.producten.add(product);
            product.voegOVChipkaartToe(this);  // Zorg ervoor dat de bidirectionele relatie wordt beheerd
        }
    }

    // Methode om een product te verwijderen
    public void verwijderProduct(Product product) {
        if (this.producten.contains(product)) {
            this.producten.remove(product);
            product.verwijderOVChipkaart(this);  // Zorg ervoor dat de bidirectionele relatie wordt beheerd
        }
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }
    public String toString() {
        return "ov_chipkaart: kaart_nummer=" + kaart_nummer + " geldig_tot=" + geldig_tot
                + " klasse=" + klasse + " saldo=" + saldo;
    }

}
