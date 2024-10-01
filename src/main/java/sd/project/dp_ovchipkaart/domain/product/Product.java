package sd.project.dp_ovchipkaart.domain.product;

import sd.project.dp_ovchipkaart.domain.OV_chipkaart.OV_chipkaart;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private int product_nummer;
    private String naam;
    private String beschrijving;
    private double prijs;
    private List<OV_chipkaart> ovchipkaarten;

    public Product() {}
    public Product(int product_nummer, String naam, String beschrijving, double prijs) {
        this.product_nummer = product_nummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
        ovchipkaarten = new ArrayList<>();
    }

    public int getProduct_nummer() {
        return product_nummer;
    }

    public void setProduct_nummer(int product_nummer) {
        this.product_nummer = product_nummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public List<OV_chipkaart> getOvchipkaarten() {
        return ovchipkaarten;
    }
    public void voegOVChipkaartToe(OV_chipkaart ovChipkaart) {
        if (!this.ovchipkaarten.contains(ovChipkaart)) {
            this.ovchipkaarten.add(ovChipkaart);
            ovChipkaart.voegProductToe(this);  // Zorg ervoor dat de bidirectionele relatie wordt beheerd
        }
    }

    // Methode om een OVChipkaart te verwijderen
    public void verwijderOVChipkaart(OV_chipkaart ovChipkaart) {
        if (this.ovchipkaarten.contains(ovChipkaart)) {
            this.ovchipkaarten.remove(ovChipkaart);
            ovChipkaart.verwijderProduct(this);  // Zorg ervoor dat de bidirectionele relatie wordt beheerd
        }
    }
}
