package projectpis;


import projectpis.dao.DAOFactory;

import projectpis.connection.ConnectionPool;
import projectpis.dao.interfaces.CustomersInterface;
import projectpis.dao.interfaces.LocationsInterface;
import projectpis.dao.interfaces.SessionsInterface;
import projectpis.entities.Customers;
import projectpis.entities.Locations;
import projectpis.entities.Sessions;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        System.out.println("\n");
        System.out.println("Testing Customers DAO");
        testCustomersDao(connectionPool);
//        System.out.println("Testing Locations DAO");
//        testLocationsDao(connectionPool);
//        System.out.println("Testing Sessions DAO");
//        testSessionsDao(connectionPool);

    }

    private static void testCustomersDao(ConnectionPool connectionPool){
        try{
            //ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            System.out.println("Current customers in DB: ");
            CustomersInterface customersDao = DAOFactory.createCustomersDao(connection);
            customersDao.printAll(customersDao.findAll());

            Scanner sc = new Scanner(System.in);
            String s = null;

            while(!"E".equals(s)) {
                System.out.println("\nFind, Insert, Delete, or EXIT? F I D E");
                s = sc.nextLine();
                switch (s) {
                    case "F" -> {
                        System.out.println("Enter id");
                        long toBeFoundId = sc.nextLong();
                        customersDao.print(customersDao.findById(toBeFoundId));
                    }
                    case "I" -> {
                        System.out.println("Enter name, phone");
                        String name = sc.nextLine();
                        String phone = sc.nextLine();
                        Customers customer = new Customers(name, phone);
                        customersDao.create(customer);
                        customersDao.printAll(customersDao.findAll());
                    }
                    case "D" -> {
                        System.out.println("Enter id");
                        long toBeDeletedId = sc.nextLong();
                        customersDao.deleteById(toBeDeletedId);
                        customersDao.printAll(customersDao.findAll());
                    }
                }
            }
            sc.close();
            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    private static void testLocationsDao(ConnectionPool connectionPool){
//        try {
//            //ConnectionPool connectionPool = ConnectionPool.getInstance();
//            Connection connection = connectionPool.getConnection();
//
//            System.out.println("Current locations in DB: ");
//            LocationsInterface locationsDao = DAOFactory.createLocationsDao(connection);
//            printAll(locationsDao.findAll());
//
//            Scanner in = new Scanner(System.in);
//            String s = null;
//
//            while(!"E".equals(s)) {
//                System.out.println("\nFind, Insert, Update, Delete, or EXIT? F I U D E");
//                s = in.nextLine();
//                switch (s) {
//                    case "F" -> {
//                        System.out.println("Enter id");
//                        long toBeFoundId = in.nextLong();
//                        locationsDao.printLocations(locationsDao.findById(toBeFoundId));
//                    }
//                    case "I" -> {
//                        System.out.println("Enter title, price");
//                        String title = in.nextLine();
//                        long price = Long.parseLong(in.nextLine());
//                        String Title = in.nextLine();
//                        Locations location = new Locations(title, price);
//                        locationsDao.createLocations(location);
//                        printAll(locationsDao.findAll());
//                    }
//                    case "U" -> {
//                        System.out.println("Enter id");
//                        long toBeUpdatedId = in.nextLong();
//                        System.out.println("Enter new price");
//                        long newPrice = in.nextLong();
//                        locationsDao.updateById(toBeUpdatedId, newPrice);
//                        printAll(locationsDao.findAll());
//                    }
//                    case "D" -> {
//                        System.out.println("Enter id");
//                        long toBeDeletedId = in.nextLong();
//                        locationsDao.deleteById(toBeDeletedId);
//                        printAll(locationsDao.findAll());
//                    }
//                }
//            }
//            in.close();
//            connectionPool.releaseConnection(connection);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    private static void printAll(List<Locations> all) {
//    }


//    private static void testSessionsDao(ConnectionPool connectionPool){
//        try{
//            //ConnectionPool connectionPool = ConnectionPool.getInstance();
//            Connection connection = connectionPool.getConnection();
//
//            System.out.println("Current sessions in DB: ");
//            SessionsInterface sessionsDAO = DAOFactory.createSessionsDao(connection);
//            List<Sessions> sessions = sessionsDAO.findAll();
//            sessions.forEach((session) -> {
//                System.out.println(session.getId()+"  "+session.getCustomer_id()+"  "+session.getLocation_id()+"  "+session.getDate()+" ");
//            });
//
//            Scanner sc = new Scanner(System.in);
//            String s = null;
//
//            while(!"E".equals(s)) {
//                System.out.println("\nFind, Insert, Delete, or EXIT? F I D E");
//                s = sc.nextLine();
//                switch (s) {
//                    case "F" -> {
//                        System.out.println("Enter id");
//                        long toBeFoundId = sc.nextLong();
//                        Sessions foundApp = sessionsDAO.findById(toBeFoundId);
//                        System.out.println(foundApp.getId()+"  "+foundApp.getCustomer_id()+"  "+foundApp.getLocation_id()+"  "+foundApp.getDate()+"  ");
//                    }
//                    case "I" -> {
//                        System.out.println("Enter date(YYYY-MM-DD), customer_id, location_id");
//
//                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                        Date fd = format.parse(sc.nextLine());
//                        java.sql.Date sqlDate = new java.sql.Date(fd.getTime());
//
//                        long customer_id = Long.parseLong(sc.nextLine());
//                        long location_id = Long.parseLong(sc.nextLine());
//
//                        Sessions session = new Sessions(customer_id, location_id, sqlDate);
//                        sessionsDAO.createSessions(session);
//
//                        sessions = sessionsDAO.findAll();
//                        sessions.forEach((sessions1) -> {
//                            System.out.println(sessions1.getId()+"  "+sessions1.getCustomer_id()+"  "+sessions1.getLocation_id()+"  "+sessions1.getDate()+" ");
//                        });
//                    }
//                    case "D" -> {
//                        System.out.println("Enter id");
//                        long toBeDeletedId = sc.nextLong();
//                        sessionsDAO.deleteById(toBeDeletedId);
//
//                        sessions = sessionsDAO.findAll();
//                        sessions.forEach((sessions2) -> {
//                            System.out.println(sessions2.getId()+"  "+sessions2.getCustomer_id()+"  "+sessions2.getLocation_id()+"  "+sessions2.getDate()+" ");
//                        });
//                    }
//                }
//            }
//            sc.close();
//            connection.close();
//        } catch (SQLException | ParseException e){
//            e.printStackTrace();
//        };
//    }
//
//
//    private static void printAll(List<Locations> locationsList){
//        locationsList.forEach((location) -> {
//            location.printLocations();
//
//        });
//    }

}