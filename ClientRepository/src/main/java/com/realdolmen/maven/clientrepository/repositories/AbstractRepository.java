package com.realdolmen.maven.clientrepository.repositories;

import com.realdolmen.maven.clientrepository.exceptions.NoQueryPossibleException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRepository<C, T> {

    public static final String LOGIN = "root";
    public static final String PASSWORD = "root";
    public static String DRIVER = "com.mysql.jdbc.Driver";
    public static String URL = "jdbc:mysql://localhost:3306/clientdb?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String tableName;
    private String idName;
    private String url;

    public AbstractRepository(String tableName, String idName) {
        this.tableName = tableName;
        this.idName = idName;
        this.url = URL;
    }

    //constructor for test db
    protected AbstractRepository(String tableName, String idName, String url) {
        this.idName = idName;
        this.tableName = tableName;
        this.url = url;
    }

    public Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, LOGIN, PASSWORD);
    }

    public List<C> findAll() throws NoQueryPossibleException {
        List<C> listToFill = null;
        try (Connection connection = createConnection()) {
            PreparedStatement pstatement = connection.prepareStatement("SELECT * FROM " + tableName);
            ResultSet resultSet = pstatement.executeQuery();
            listToFill = new ArrayList<>();
            while (resultSet.next()) {
                listToFill.add(createObject(resultSet));
            }
        } catch (Exception e) {
            throw new NoQueryPossibleException("Find all " + tableName + " can not be excecuted");
        }
        return listToFill;
    }

    public C findById(T id) throws NoQueryPossibleException {
        C object = null;
        try (Connection connection = createConnection()) {
            PreparedStatement pstatement = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE " + idName + " = " + id);
            ResultSet resultSet = pstatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("in resultset");
                object = createObject(resultSet);
            }
        } catch (Exception e) {
            throw new NoQueryPossibleException("Find by id " + tableName + " can not be excecuted");
        }
        return object;
    }

    public boolean deleteItem(T id) throws NoQueryPossibleException {
        //Not tested yet
        try (Connection connection = createConnection()) {
            PreparedStatement pstatement = connection.prepareStatement("DELETE FROM " + tableName + " WHERE " + idName + " = " + id);
            pstatement.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new NoQueryPossibleException("Find by id " + tableName + " can not be excecuted");
        }
    }

    public void updateItem(C item) throws NoQueryPossibleException {
        //try it when you are curious, or have the time
    }

    public C insertItem(C item) throws NoQueryPossibleException {
        //Only tested for postalcode
        try (Connection connection = createConnection()) {
            String query = "INSERT INTO " + tableName + getColumnString() + " values " + getValuesString(item);
            System.out.println(query);
            PreparedStatement pstatement = connection.prepareStatement(query);
            pstatement.executeUpdate();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            throw new NoQueryPossibleException("Insert " + tableName + " can not be excecuted ");
        }
    }

    //abstract method which returns an object created in the subclass but injected in the methods
    public abstract C createObject(ResultSet resultSet);

    public abstract String getColumnString();

    public abstract String getValuesString(C c);

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
