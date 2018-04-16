/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Mayara
 */
public class ConnectionDB {
    public static Connection Conector() {
        java.sql.Connection conexao = null;
        
        String driver = "com.mysql.jdbc.Driver";
        
        String url = "jdbc:mysql://localhost/bdarquitetura?autoReconnect=true&useSSL=false";
        String user = "root";
        String password = "wi-fi147";
        
        try {
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            System.err.println("\n============================================");
            System.err.println("\nCLASSE VISITAS CONECTA BANCO");
            System.err.println("\nERRO NO MÉTODO GET CONNECTION");
            System.err.println("\n " + e.getCause());
            System.err.println("\n " + e.getMessage());
            System.err.println("\n============================================");
            throw new RuntimeException(e);
        }
    }
}
