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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rik Schaaf aka CC007 <http://coolcat007.nl/>
 */
public class DatabaseRetrieval extends DatabaseConnection {

    public int getParticipantCount() {
        return getValue("SELECT Count(DISTINCT userId) AS participantsCount FROM statistics;", "participantsCount");
    }

    public int getMalePercentage() {
        int maleCount = getValue("SELECT Count(DISTINCT userId) AS participantsCount FROM statistics WHERE gender='1';", "participantsCount");
        int totalCount = getParticipantCount();
        return totalCount == 0 ? 100 : (maleCount * 100) / totalCount;
    }

    public int getFemalePercentage() {
        return 100 - getMalePercentage();
    }

    public int getYoungerPercentage() {
        int youngerCount = getValue("SELECT Count(DISTINCT userId) AS participantsCount FROM statistics WHERE ageGroup='0';", "participantsCount");
        int totalCount = getParticipantCount();
        return totalCount == 0 ? 100 : (youngerCount * 100) / totalCount;
    }

    public int getOlderPercentage() {
        return 100 - getYoungerPercentage();
    }

    public int getAnsweredCount() {
        return getValue("SELECT Count(*) AS answeredCount FROM score;", "answeredCount");
    }

    public int getRightlyAnsweredCount() {
        return getValue("SELECT Count(*) AS answeredCount FROM score WHERE correctlyAnswered='1';", "answeredCount");
    }

    public List<Integer> getGenderStatistics() {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i >= 0; i--) {
            int answered = getValue("SELECT Count(*) AS answeredCount FROM statistics, score WHERE statistics.userId=score.userId AND gender='" + i + "';", "answeredCount");
            int coreclyAnswered = getValue("SELECT Count(*) AS answeredCount FROM statistics, score WHERE statistics.userId=score.userId AND gender='" + i + "' AND correctlyAnswered='1';", "answeredCount");
            result.add(answered == 0 ? 100 : (coreclyAnswered*100)/answered);
        }
        result.add(getValue("SELECT Count(*) AS answeredCount FROM score WHERE correctlyAnswered='1';", "answeredCount"));
        return result;
    }

    public List<Integer> getAgeGroupStatistics() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            int answered = getValue("SELECT Count(*) AS answeredCount FROM statistics, score WHERE statistics.userId=score.userId AND ageGroup='" + i + "';", "answeredCount");
            int coreclyAnswered = getValue("SELECT Count(*) AS answeredCount FROM statistics, score WHERE statistics.userId=score.userId AND ageGroup='" + i + "' AND correctlyAnswered='1';", "answeredCount");
            result.add(answered == 0 ? 100 : (coreclyAnswered*100)/answered);
        }
        result.add(getValue("SELECT Count(*) AS answeredCount FROM score WHERE correctlyAnswered='1';", "answeredCount"));
        return result;
    }

    public List<Integer> getQuestionStatistics() {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= 11; i++) {
            int answered = getValue("SELECT Count(*) AS answeredCount FROM statistics, score WHERE statistics.userId=score.userId AND questionId='" + i + "';", "answeredCount");
            int coreclyAnswered = getValue("SELECT Count(*) AS answeredCount FROM statistics, score WHERE statistics.userId=score.userId AND questionId='" + i + "' AND correctlyAnswered='1';", "answeredCount");
            result.add(answered == 0 ? 100 : (coreclyAnswered*100)/answered);
        }
        result.add(getValue("SELECT Count(*) AS answeredCount FROM score WHERE correctlyAnswered='1';", "answeredCount"));
        return result;
    }

    protected int getValue(String query, String columnName) {
        int result = -1;
        try {
            startConnection();
            Statement stmt;
            ResultSet rs;
            c.setAutoCommit(false);
            stmt = c.createStatement();
            rs = stmt.executeQuery(query);
            rs.next();
            result = rs.getInt(columnName);
            c.commit();
            rs.close();
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
