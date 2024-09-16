package sd.project.dp_ovchipkaart.domain.adres;

public class Adres {
    private int id;
    private String postcode;
    private String huisnummer;
    private String straat;
    private String woonplaats;
    private int reizigerId;

    public Adres() {}

    public Adres(int id, String postcode, String huisnummer, String straat, String woonplaats, int reizigerId) {
        this.id = id;
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.straat = straat;
        this.woonplaats = woonplaats;
        this.reizigerId = reizigerId;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }
    public int getReizigerId() {
        return reizigerId;
    }

    public void setReizigerId(int reizigerId) {
        this.reizigerId = reizigerId;
    }

    public String toString() {
        return "Adres: " + id + ", " + postcode + ", "
                + straat + ", " + huisnummer + ", " + woonplaats;
    }
}
