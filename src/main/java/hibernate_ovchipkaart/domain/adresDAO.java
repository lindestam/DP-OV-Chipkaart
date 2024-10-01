package hibernate_ovchipkaart.domain;

import sd.project.dp_ovchipkaart.domain.adres.Adres;
import sd.project.dp_ovchipkaart.domain.reiziger.Reiziger;

import java.util.List;

public interface adresDAO {
    void save(sd.project.dp_ovchipkaart.domain.adres.Adres adres);
    void delete(sd.project.dp_ovchipkaart.domain.adres.Adres adres);
    void update(sd.project.dp_ovchipkaart.domain.adres.Adres adres);
    sd.project.dp_ovchipkaart.domain.adres.Adres findByReiziger(Reiziger reiziger);
    List<Adres> findAll();
}
