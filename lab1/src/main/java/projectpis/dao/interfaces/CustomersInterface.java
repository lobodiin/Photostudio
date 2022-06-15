package projectpis.dao.interfaces;

import projectpis.entities.Customers;
import java.util.List;

public interface CustomersInterface {
    Customers findById(long id);
    Customers findByName(String name);
    List<Customers> findAll();
    void create(Customers customers);
    void deleteById(long id);
    void print(Customers customers);
    void printAll(List<Customers> customers);
}
