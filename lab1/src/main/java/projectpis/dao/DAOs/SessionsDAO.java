package projectpis.dao.DAOs;


import projectpis.dao.interfaces.SessionsInterface;
import projectpis.entities.Sessions;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SessionsDAO implements SessionsInterface {

    private Connection connection;
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_CUSTOMERID = "customer_id";
    private final static String COLUMN_LOCATIONID = "location_id";
    private final static String COLUMN_DATE = "date";
    private Statement statement;

    public SessionsDAO(Connection connection) throws SQLException {
        this.connection = connection;
        this.statement = connection.createStatement();
    }

    private Sessions getSessions(ResultSet resultSet) throws SQLException{
        long id = resultSet.getLong(COLUMN_ID);
        long customer_id = resultSet.getLong(COLUMN_CUSTOMERID);
        long location_id = resultSet.getLong(COLUMN_LOCATIONID);
        Date date = resultSet.getDate(COLUMN_DATE);

        return new Sessions(id, customer_id, location_id, date);
    }
    @Override
    public void printSessions(Sessions sessions){
        System.out.println(sessions.getId()+" "+sessions.getLocation_id()+" "+sessions.getDate()+" ");
    }

    @Override
    public Sessions findById(long id) {
        String query = "SELECT * FROM sessions WHERE sessions.id=" + id;

        Sessions sessions = new Sessions();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();
            sessions = getSessions(resultSet);

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return sessions;
    }

    @Override
    public List<Sessions> findAll() {
        String query = "SELECT * FROM sessions";
        List<Sessions> sessionsList = new ArrayList<Sessions>();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Sessions sessions = getSessions(resultSet);
                sessionsList.add(sessions);
            }

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return sessionsList;
    }

    @Override
    public long createSessions(Sessions sessions) {
        String query = "INSERT INTO sessions (customer_id, location_id, date) VALUES ('";
        query += sessions.getCustomer_id()+"', '"
                +sessions.getLocation_id()+"', '"
                +sessions.getDate()+"') " ;

        long createdId = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                createdId = resultSet.getLong(1);
            }
            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return createdId;
    }

    @Override
    public void updateById(long id, long newDate) {
        String query = "UPDATE sessions SET date = "+newDate+" WHERE id="+id;

        try {
            Statement statement = connection.createStatement();
            long newId = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            statement.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteById(long id) {
        String query = "DELETE FROM sessions WHERE id="+id;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }
}
