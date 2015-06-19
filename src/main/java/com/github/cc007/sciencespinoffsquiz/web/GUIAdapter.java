/* 
 * The MIT License
 *
 * Copyright 2015 Rik Schaaf aka CC007 <http://coolcat007.nl/>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.cc007.sciencespinoffsquiz.web;

import com.github.cc007.sciencespinoffsquiz.language.LanguagesController;
import com.github.cc007.sciencespinoffsquiz.quiz.controller.GameAdapter;
import com.github.cc007.sciencespinoffsquiz.quiz.model.Answer;
import com.github.cc007.sciencespinoffsquiz.quiz.model.Question;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rik Schaaf aka CC007 <http://coolcat007.nl/>
 */
public class GUIAdapter extends Observable implements GameAdapter, Observer {

    private final LanguagesController lc;
    private Question q;
    Map<String, Object> data;

    public GUIAdapter(LanguagesController lc) {
        data = new HashMap<>();
        data.put("language", null);
        data.put("gender", null);
        data.put("ageGroup", null);
        data.put("givenAnswer", null);
        this.lc = null;//new LanguagesController(this.getLanguage());
        this.q = null;
    }

    @Override
    public String askLanguage() {
        while (data.get("language") == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GUIAdapter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (String) data.get("language");
    }

    @Override
    public String getLanguage() {
        if (data.get("language") == null) {
            askLanguage();
        }
        return (String) data.get("language");
    }

    @Override
    public String askGender() {
        while (data.get("gender") == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GUIAdapter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (String) data.get("gender");
    }

    @Override
    public String askAgeGroup() {
        while (data.get("ageGroup") == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GUIAdapter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (String) data.get("ageGroup");
    }

    @Override
    public void setQuestion(Question question) {
        q = question;
        data.put("givenAnswer", null);
    }

    @Override
    public void askQuestion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showPossibleAnswers() {
        Map<String, Object> answers = new HashMap<>();
        answers.put("answerA", q.getAnswerA());
        answers.put("answerB", q.getAnswerB());
        answers.put("answerC", q.getAnswerC());
        setChanged();
        notifyObservers(answers);
    }

    @Override
    public Answer getGivenAnswer() {
        while (data.get("givenAnswer") == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GUIAdapter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (Answer) data.get("givenAnswer");
    }

    @Override
    public void showRightAnswer(boolean correctlyAnswered) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showExplanation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void endGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Observable o, Object arg) {
        
    }
    
    public Question getQuestion(){
        return q;
    }

}
