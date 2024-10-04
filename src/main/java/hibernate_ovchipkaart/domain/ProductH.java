package hibernate_ovchipkaart.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "product")
public class ProductH {
    @Column(name = "product_nummer")
    private int product_nummer;

    @Column(name = "naam")
    private String naam;

    @Column(name = "beschrijving")
    private String beschrijving;

    @Column(name = "prijs")
    private double prijs;

    @ManyToMany(mappedBy = "producten", fetch = FetchType.LAZY)
    private List<OV_Chipkaart> ovchipkaarten = new ArrayList<>();


    public ProductH(String beschrijving, String naam, int product_nummer, double prijs) {
        this.beschrijving = beschrijving;
        this.naam = naam;
        this.product_nummer = product_nummer;
        this.prijs = prijs;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
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

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public List<OV_Chipkaart> getOvchipkaarten() {
        return ovchipkaarten;
    }

    public void setOvchipkaarten(List<OV_Chipkaart> ovchipkaarten) {
        this.ovchipkaarten = ovchipkaarten;
    }
    public void voegOVChipkaartToe(OV_Chipkaart ovChipkaart) {
        ovchipkaarten.add(ovChipkaart);
        ovChipkaart.getProducten().add(this); // Houd de relatie bidirectioneel
    }

    public void verwijderOVChipkaart(OV_Chipkaart ovChipkaart) {
        ovchipkaarten.remove(ovChipkaart);
        ovChipkaart.getProducten().remove(this); // Houd de relatie bidirectioneel
    }
}
