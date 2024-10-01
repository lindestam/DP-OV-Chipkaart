package hibernate_ovchipkaart.domain;

import sd.project.dp_ovchipkaart.domain.reiziger.Reiziger;

import javax.persistence.*;

@Entity
@Table(name = "adres")
public class AdresH {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adres_id")
    private int id;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "huisnummer")
    private String huisnummer;

    @Column(name = "straat")
    private String straat;

    @Column(name = "woonplaats")
    private String woonplaats;

    // Relatie met Reiziger
    @OneToOne(mappedBy = "adres", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private ReizigerH reiziger;

    public AdresH() {}

    // Getters en Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public ReizigerH getReiziger() {
        return reiziger;
    }

    public void setReiziger(ReizigerH reiziger) {
        this.reiziger = reiziger;
    }

    @Override
    public String toString() {
        return "Adres{" +
                "id=" + id +
                ", postcode='" + postcode + '\'' +
                ", huisnummer='" + huisnummer + '\'' +
                ", straat='" + straat + '\'' +
                ", woonplaats='" + woonplaats + '\'' +
                ", reiziger=" + (reiziger != null ? reiziger.getId() : null) +
                '}';
    }
}
