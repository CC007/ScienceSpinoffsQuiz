/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.cc007.sciencespinoffsquiz.quiz.storage;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rik Schaaf aka CC007 <http://coolcat007.nl/>
 */
public class DatabaseRetrieval {

    public int getParticipantCount() {
        return 42;
    }

    public int getMalePercentage() {
        return 51;
    }

    public int getFemalePercentage() {
        return 100 - getMalePercentage();
    }
    
    public int getYoungerPercentage() {
        return 52;
    }

    public int getOlderPercentage() {
        return 100 - getYoungerPercentage();
    }
    
    public int getAnsweredCount(){
        return 314;
    }
    
    public int getRightlyAnsweredCount(){
        return 159;
    }
    
    public List<Integer> getGenderStatistics(){
        List<Integer> result =  new ArrayList<>();
        result.add(57);
        result.add(55);
        return result;
    }
    
    public List<Integer> getAgeGroupStatistics(){
        List<Integer> result =  new ArrayList<>();
        result.add(60);
        result.add(49);
        return result;
    }
    
    public List<Integer> getQuestionStatistics(){
        List<Integer> result =  new ArrayList<>();
        result.add(30);
        result.add(49);
        result.add(60);
        result.add(59);
        result.add(40);
        result.add(59);
        result.add(50);
        result.add(39);
        result.add(63);
        result.add(44);
        result.add(67);
        return result;
    }
}
