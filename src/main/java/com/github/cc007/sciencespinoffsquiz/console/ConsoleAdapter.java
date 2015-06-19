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
package com.github.cc007.sciencespinoffsquiz.console;

import java.util.Scanner;
import com.github.cc007.sciencespinoffsquiz.language.LanguagesController;
import com.github.cc007.sciencespinoffsquiz.quiz.controller.GameAdapter;
import com.github.cc007.sciencespinoffsquiz.quiz.model.Answer;
import com.github.cc007.sciencespinoffsquiz.quiz.model.Question;

/**
 *
 * @author Rik Schaaf aka CC007 <http://coolcat007.nl/>
 */
public class ConsoleAdapter implements GameAdapter {

    private final Scanner s;
    private final LanguagesController lc;
    private Question q;
    private String language;

    public ConsoleAdapter() {
        this.s = new Scanner(System.in);
        this.lc = null;//new LanguagesController(this.getLanguage());
        this.q = null;
    }

    @Override
    public String askLanguage() {
        String result = null;
        System.out.println("Kies een taal/Choose a language: Nederlands/English: ");
        while (true) {
            result = s.next();
            if (result.equalsIgnoreCase("Nederlands") || result.equalsIgnoreCase("English")) {
                break;
            }
            System.out.println("Foutieve invoer, probeer opnieuw. / The input is not valid, try again.");
        }
        System.out.println();
        return result;
    }

    @Override
    public final String getLanguage() {
        if (language == null) {
            language = askLanguage();
        }
        return language;
    }

    @Override
    public String askGender() {
        System.out.println(lc.getText("AskGender"));
        String name = s.next();
        System.out.println();
        return name;
    }

    @Override
    public String askAgeGroup() {
        System.out.println(lc.getText("AskAgeGroup"));
        String name = s.next();
        System.out.println();
        return name;
    }

    @Override
    public void setQuestion(Question question) {
        if (q != null) {
            System.out.println(lc.getText("NextQuestion"));
            s.nextLine();
            s.nextLine();
        }
        q = question;
    }

    @Override
    public void askQuestion() {
        System.out.println(q.getQuestion());
    }

    @Override
    public void showPossibleAnswers() {
        System.out.println(" A: " + q.getAnswerA());
        System.out.println(" B: " + q.getAnswerB());
        System.out.println(" C: " + q.getAnswerC());
    }

    @Override
    public Answer getGivenAnswer() {
        System.out.println(lc.getText("AskAnswer"));
        Answer answer = null;
        while (answer == null) {
            answer = Answer.fromString(s.next());
            if (answer == null) {
                System.out.println(lc.getText("InvalidAnswer"));
            }
        }
        return answer;
    }

    @Override
    public void showRightAnswer(boolean correctlyAnswered) {
        if (correctlyAnswered) {
            System.out.print(lc.getText("RightAnswer"));
        } else {
            System.out.print(lc.getText("WrongAnswer"));
        }
        System.out.println(q.getRightAnswer().toString());
        System.out.println();
    }

    @Override
    public void showExplanation() {
        System.out.println(q.getExplanation());
        System.out.println();
    }

    @Override
    public void endGame() {
        System.out.println(lc.getText("EndGame"));
    }

}
