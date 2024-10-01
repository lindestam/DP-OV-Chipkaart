package hibernate_ovchipkaart.domain;

import java.util.List;

public interface AdresDAOH {
    void save(AdresH adres);
    void delete(AdresH adres);
    void update(AdresH adres);
    AdresH findByReiziger(ReizigerH reiziger);
    List<AdresH> findAll();
}
