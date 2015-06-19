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
package com.github.cc007.sciencespinoffsquiz.quiz.controller;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import com.github.cc007.sciencespinoffsquiz.quiz.model.Answer;
import com.github.cc007.sciencespinoffsquiz.quiz.model.Question;
import com.github.cc007.sciencespinoffsquiz.quiz.model.QuestionPool;
import com.github.cc007.sciencespinoffsquiz.quiz.storage.GameStatisticsStorage;

/**
 *
 * @author Rik Schaaf aka CC007 <http://coolcat007.nl/>
 */
public class GameMaster extends Thread{

    private final GameAdapter gameAdapter;
    private final QuestionPool questionPool;
    private final GameStatisticsStorage gameStatisticsStorage;

    public GameMaster(GameAdapter gameAdapter, String xmlFileName, GameStatisticsStorage gameStatisticsStorage) {
        this.gameAdapter = gameAdapter;
        this.questionPool = new QuestionPool(xmlFileName);
        this.gameStatisticsStorage = gameStatisticsStorage;
    }

    public void startGame() {
        this.start();
    }

    @Override
    public void run() {
        String gender = gameAdapter.askGender();
        gameStatisticsStorage.setGender(gender);
        String ageGroup = gameAdapter.askAgeGroup();
        gameStatisticsStorage.setAgeGroup(ageGroup);
        List<Question> questions = questionPool.getQuestions();
        Collections.shuffle(questions, new Random(System.currentTimeMillis()));
        for (Question question : questions) {
            gameAdapter.setQuestion(question);
            gameAdapter.askQuestion();
            gameAdapter.showPossibleAnswers();
            Answer answer = gameAdapter.getGivenAnswer();
            if (answer.equalsAnswer(question.getRightAnswer())) {
                gameAdapter.showRightAnswer(true);
                gameStatisticsStorage.setScore(question.getId(), true);
            } else {
                gameAdapter.showRightAnswer(false);
                gameStatisticsStorage.setScore(question.getId(), false);
            }
            gameAdapter.showExplanation();
        }
        gameStatisticsStorage.saveStatistics();
        gameAdapter.endGame();
    }
    
    
}
