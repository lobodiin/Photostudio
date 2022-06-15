package projectpis.dao.interfaces;

import projectpis.entities.Sessions;

import java.util.List;

public interface SessionsInterface {
    Sessions findById(long id);
    List<Sessions> findAll();
    long createSessions(Sessions sessions);
    public void printSessions(Sessions sessions);
    public void updateById(long id, long newDate);
    void deleteById(long id);
}
