package hibernate_ovchipkaart.domain;

import java.util.List;

public interface ReizigerDAOH {
    boolean save(ReizigerH reiziger);
    boolean update(ReizigerH reiziger);
    boolean delete(ReizigerH reiziger);
    ReizigerH findById(int id);
    List<ReizigerH> findAll();
}

