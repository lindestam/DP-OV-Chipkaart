import jakarta.transaction.SystemException;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


import java.sql.SQLException;
import java.util.List;

import java.time.LocalDate;
import java.util.List;

public interface ReizigerDAO {
    boolean save(Reiziger reiziger);
    boolean update(Reiziger reiziger);
    boolean delete(Reiziger reiziger);
    Reiziger findById(int id);
    List<Reiziger> findAll();
}

