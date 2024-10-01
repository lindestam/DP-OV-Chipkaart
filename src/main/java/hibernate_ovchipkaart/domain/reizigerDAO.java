package hibernate_ovchipkaart.domain;

import sd.project.dp_ovchipkaart.domain.reiziger.Reiziger;

import java.util.List;

public interface reizigerDAO {
    boolean save(sd.project.dp_ovchipkaart.domain.reiziger.Reiziger reiziger);
    boolean update(sd.project.dp_ovchipkaart.domain.reiziger.Reiziger reiziger);
    boolean delete(sd.project.dp_ovchipkaart.domain.reiziger.Reiziger reiziger);
    sd.project.dp_ovchipkaart.domain.reiziger.Reiziger findById(int id);
    List<Reiziger> findAll();
}

