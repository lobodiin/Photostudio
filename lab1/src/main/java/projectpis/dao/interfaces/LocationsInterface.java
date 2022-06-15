package projectpis.dao.interfaces;


import projectpis.entities.Locations;

import java.util.List;

public interface LocationsInterface {
    Locations findById(long id);
    List<Locations> findAll();
    long createLocations (Locations locations);
    void updateById (long id, long newPrice);
    void deleteById(long id);
    void printLocations(Locations locations);
}