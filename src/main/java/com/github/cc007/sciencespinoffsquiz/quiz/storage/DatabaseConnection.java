/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.sciencespinoffsquiz.quiz.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rik Schaaf aka CC007 <http://coolcat007.nl/>
 */
public abstract class DatabaseConnection {

    protected Connection c = null;

    public DatabaseConnection() {
        checkDB();
    }

    
    
    protected void startConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite::resource:statistics.db");
            System.out.println("Connection: " + c);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DatabaseStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void checkDB() {
        try {
            startConnection();
            Statement stmt;
            ResultSet rs;
            boolean exists;
            c.setAutoCommit(false);
            stmt = c.createStatement();
            rs = stmt.executeQuery("SELECT * FROM sqlite_master WHERE name='score' and type='table';");
            exists = rs.next();
            rs.close();
            stmt.close();
            if (!exists) {
                stmt = c.createStatement();
                String sql = "CREATE TABLE score "
                        + "(userId             TEXT    NOT NULL, "
                        + " questionId         TEXT    NOT NULL, "
                        + " correctlyAnswered  INT     NOT NULL, "
                        + " CONSTRAINT pk_userQuestion PRIMARY KEY (userId,questionId))";
                stmt.executeUpdate(sql);
                stmt.close();
                c.commit();
            }

            stmt = c.createStatement();
            rs = stmt.executeQuery("SELECT * FROM sqlite_master WHERE name='statistics' and type='table';");
            exists = rs.next();
            rs.close();
            stmt.close();
            if (!exists) {
                stmt = c.createStatement();
                String sql = "CREATE TABLE statistics "
                        + "(userId      TEXT    PRIMARY KEY NOT NULL, "
                        + " gender      INT                 NOT NULL, "
                        + " ageGroup    INT                 NOT NULL)";
                stmt.executeUpdate(sql);
                stmt.close();
                c.commit();
            }
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
