import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reiziger")
public class Reiziger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reiziger_id")
    private int id;

    @Column(name = "voorletters")
    private String voorletters;

    @Column(name = "tussenvoegsel")
    private String tussenvoegsel;

    @Column(name = "achternaam")
    private String achternaam;

    @Column(name = "geboortedatum")
    private LocalDate geboortedatum;

    // Relatie met Adres
    @OneToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "adres_id", referencedColumnName = "adres_id")
    private Adres adres;

    @OneToMany(mappedBy = "reiziger")
    @Cascade(CascadeType.ALL)
    private List<OV_chipkaart> ovChipkaarten = new ArrayList<>();

    public Reiziger(int i, String s, String string, String boers, Date date) {
        this.id = i;
        this.voorletters = s;
        this.tussenvoegsel = string;
        this.achternaam = boers;
        geboortedatum = date.toLocalDate();
    }


    // Getters en Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(LocalDate geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
        if (adres != null) {
            adres.setReiziger(this);
        }
    }

    @Override
    public String toString() {
        return "Reiziger{" +
                "id=" + id +
                ", voorletters='" + voorletters + '\'' +
                ", tussenvoegsel='" + tussenvoegsel + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", geboortedatum=" + geboortedatum +
                ", adres=" + (adres != null ? adres.toString() : null) +
                '}';
    }

    public List<OV_chipkaart> getOvChipkaarten() {
        return ovChipkaarten;
    }

    public void setOvChipkaarten(List<OV_chipkaart> ovChipkaarten) {
        this.ovChipkaarten = ovChipkaarten;
    }
}
