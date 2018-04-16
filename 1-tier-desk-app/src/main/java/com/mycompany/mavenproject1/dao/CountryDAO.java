package com.mycompany.mavenproject1.dao;

import java.util.Set;

import com.mycompany.mavenproject1.data.Country;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import com.mycompany.mavenproject1.utils.ConnectionDB;

public class CountryDAO {

    private Connection conn;
    
    public CountryDAO(){
        this.conn =  ConnectionDB.Conector();
    }

    public void create(Country country) throws Exception {
        try{

            String sql = "INSERT INTO country (name, acronym, digits) VALUES (?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, country.getName());
            statement.setString(2, country.getAcronym());
            statement.setInt(3, country.getDigits());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted == 0) {
                throw new RuntimeException("Country could not be persisted!");
            }

        } catch (SQLException ex) {
            System.err.println("\n============================================");
            System.err.println("\nCLASS COUNTRY DAO");
            System.err.println("\nERROR ON CREATE");
            System.err.println("\nCAUSE: " + ex.getCause());
            System.err.println("\nMESSAGE: " + ex.getMessage());
            ex.printStackTrace();
            System.err.println("\n============================================");
            throw new RuntimeException(ex);
        }
    }

    public Country readById(int id) {
        return this.readAll().
                stream().
                filter(
                        country -> country.getId() == id).
                findAny().
                get();
    }

    public Country readByAcronym(String acronym) {
        return this.readAll().
                stream().
                filter(
                        country -> country.getAcronym().
                                equalsIgnoreCase(acronym)).
                findAny().
                get();
    }

    public Country readByName(String name) {
        return this.readAll().
                stream().
                filter(
                        country -> country.getName().
                                equalsIgnoreCase(name)).
                findAny().
                get();
    }

    public Set<Country> readAll() {
        Set<Country> resultSet = new HashSet<>();

        try{

            String sql = "SELECT * FROM country";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                resultSet.add(new Country(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("acronym"),
                        result.getInt("digits")
                ));
            }

        } catch (SQLException ex) {
            System.err.println("\n============================================");
            System.err.println("\nCLASS COUNTRY DAO");
            System.err.println("\nERROR ON READALL");
            System.err.println("\nCAUSE: " + ex.getCause());
            System.err.println("\nMESSAGE: " + ex.getMessage());
            ex.printStackTrace();
            System.err.println("\n============================================");
            throw new RuntimeException(ex);
        }

        return resultSet;
    }

    public void update(Country newCountry, String name) {
        try{

            String sql = "UPDATE country SET name=?, acronym=?, digits=? WHERE name like ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, newCountry.getName());
            statement.setString(2, newCountry.getAcronym());
            statement.setInt(3, newCountry.getDigits());
            statement.setString(4, name);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted == 0) {
                throw new RuntimeException("Country could not be updated!");
            }

        } catch (SQLException ex) {
            System.err.println("\n============================================");
            System.err.println("\nCLASS COUNTRY DAO");
            System.err.println("\nERROR ON UPDATE");
            System.err.println("\nCAUSE: " + ex.getCause());
            System.err.println("\nMESSAGE: " + ex.getMessage());
            ex.printStackTrace();
            System.err.println("\n============================================");
            throw new RuntimeException(ex);
        }
    }

    public void delete(String name) {
        try{

            String sql = "DELETE FROM country WHERE name like ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted == 0) {
                throw new RuntimeException("Country could not be deleted!");
            }

        } catch (SQLException ex) {
            System.err.println("\n============================================");
            System.err.println("\nCLASS COUNTRY DAO");
            System.err.println("\nERROR ON DELETE");
            System.err.println("\nCAUSE: " + ex.getCause());
            System.err.println("\nMESSAGE: " + ex.getMessage());
            ex.printStackTrace();
            System.err.println("\n============================================");
            throw new RuntimeException(ex);
        }
    }

}
