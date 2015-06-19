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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rik Schaaf aka CC007 <http://coolcat007.nl/>
 */
public class DatabaseStorage implements GameStatisticsStorage {

    private int userId;
    private String gender;
    private String ageGroup;
    private Map<Integer, Boolean> score;
    private Connection c = null;

    public DatabaseStorage() {
        setNewUserId();
        this.score = new HashMap<>();
    }

    public DatabaseStorage(int userId) {
        checkDB();
        if (checkUserId(userId)) {
            this.userId = userId;
        }
        this.score = new HashMap<>();
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    @Override
    public void setScore(int questionId, boolean rightlyAnswered) {
        this.score.put(questionId, rightlyAnswered);
    }

    @Override
    public void saveStatistics() {
        try {
            startConnection();
            Statement stmt;
            ResultSet rs;
            String sql;
            boolean exists;
            c.setAutoCommit(false);
            stmt = c.createStatement();
            rs = stmt.executeQuery("SELECT * FROM statistics WHERE userId='" + userId + "';");
            exists = rs.next();
            rs.close();
            stmt.close();
            if (!exists) {
                stmt = c.createStatement();
                sql = "INSERT INTO statistics (userId,gender,ageGroup) "
                        + "VALUES ('" + userId + "', " + (gender.equals("male") ? 1 : 0) + ", " + (ageGroup.equals("younger") ? 0 : 1) + " );";
                stmt.executeUpdate(sql);
                stmt.close();
                c.commit();
            }
            stmt = c.createStatement();
            for (Map.Entry<Integer, Boolean> entrySet : score.entrySet()) {
                sql = "INSERT INTO score (userId,questionId,correctlyAnswered) "
                        + "VALUES ('" + userId + "', " + entrySet.getKey() + ", " + (entrySet.getValue() ? 1 : 0) + " );";
                stmt.executeUpdate(sql);
            }

            stmt.close();
            c.commit();
            score = new HashMap<>();
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getUserId() {
        return userId;
    }

    private void startConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite::resource:statistics.db");
            System.out.println("Connection: " + c);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DatabaseStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean checkUserId(int userId) {
        try {
            startConnection();
            Statement stmt;
            ResultSet rs;
            boolean exists;
            c.setAutoCommit(false);
            stmt = c.createStatement();
            rs = stmt.executeQuery("SELECT * FROM statistics WHERE userId='" + userId + "';");
            exists = rs.next();
            rs.close();
            stmt.close();
            c.close();
            return exists;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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

    private void setNewUserId() {
        try {
            checkDB();
            startConnection();
            Statement stmt;
            ResultSet rs;
            c.setAutoCommit(false);
            stmt = c.createStatement();
            rs = stmt.executeQuery("SELECT MAX(userId) AS highestId FROM statistics");
            if (rs.next()) {
                userId = rs.getInt("highestId") + 1;
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
