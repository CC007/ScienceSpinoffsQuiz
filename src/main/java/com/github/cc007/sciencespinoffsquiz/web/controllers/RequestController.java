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
package com.github.cc007.sciencespinoffsquiz.web.controllers;

import com.github.cc007.sciencespinoffsquiz.language.LanguagesController;
import com.github.cc007.sciencespinoffsquiz.quiz.model.Answer;
import com.github.cc007.sciencespinoffsquiz.quiz.model.Question;
import com.github.cc007.sciencespinoffsquiz.quiz.model.QuestionPool;
import com.github.cc007.sciencespinoffsquiz.quiz.storage.DatabaseRetrieval;
import com.github.cc007.sciencespinoffsquiz.quiz.storage.DatabaseStorage;
import com.github.cc007.sciencespinoffsquiz.quiz.storage.GameStatisticsStorage;
import com.github.cc007.sciencespinoffsquiz.util.IntegerChecker;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Rik Schaaf aka CC007 <http://coolcat007.nl/>
 */
@Controller
public class RequestController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map) {
        map.put("quizredirect", "<meta http-equiv=\"refresh\" content=\"0;url=/ScienceSpinoffsQuiz/quiz\" />");
        return "index";
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public String statistics(ModelMap map) {
        DatabaseRetrieval dr = new DatabaseRetrieval();
        map.put("participantsCount", dr.getParticipantCount() + "");
        map.put("malePercentage", dr.getMalePercentage() + "");
        map.put("femalePercentage", dr.getFemalePercentage() + "");
        map.put("youngerPercentage", dr.getYoungerPercentage() + "");
        map.put("olderPercentage", dr.getOlderPercentage() + "");
        map.put("answeredCount", dr.getAnsweredCount() + "");
        map.put("rightlyAnsweredCount", dr.getRightlyAnsweredCount() + "");
        List<Integer> genderStatistics = dr.getGenderStatistics();
        List<Integer> ageGroupStatistics = dr.getAgeGroupStatistics();
        List<Integer> questionStatistics = dr.getQuestionStatistics();
        for (int i = 0; i < 2; i++) {
            map.put("gender" + (i+1),genderStatistics.get(i));
            map.put("ageGroup" + (i+1),ageGroupStatistics.get(i));
        }
        for (int i = 0; i < 11; i++) {
            map.put("question" + (i+1),questionStatistics.get(i));
        }
        return "statistics";
    }

    @RequestMapping(value = "/explanation", method = RequestMethod.GET)
    public String explanation(ModelMap map) {
        map.put("statisticsredirect", "<meta http-equiv=\"refresh\" content=\"15;url=/ScienceSpinoffsQuiz/statistics\" />");
        map.put("statisticshide", " style=\"border:none;background-color:inherit;\"");
        QuestionPool qp = new QuestionPool("questions_en.xml");
        List<Question> questions = qp.getQuestions();
        Collections.shuffle(questions, new Random(System.currentTimeMillis()));
        Question q = questions.get(0);
        map.put("imgName", q.getExplanationImage());
        map.put("explanation", q.getExplanation());
        return "explanation";
    }

    @RequestMapping(value = "/quiz", method = RequestMethod.GET)
    public String quiz(
            @ModelAttribute("userid") String userId,
            @ModelAttribute("answer") String answer,
            @ModelAttribute("random") String random,
            @ModelAttribute("questionNr") String questionNr,
            @ModelAttribute("language") String language,
            @ModelAttribute("gender") String gender,
            @ModelAttribute("ageGroup") String ageGroup,
            @ModelAttribute("restart") String restart,
            ModelMap map) {
        long newRandom;
        GameStatisticsStorage gss;
        LanguagesController lc;
        System.out.println("Incoming request:");
        if("restart".equals(restart)){
            System.out.println(" Restart quiz");
            return "index";
        }
        if (!"".equals(userId) && IntegerChecker.isInteger(userId)) {
            gss = new DatabaseStorage(Integer.parseInt(userId));
            System.out.println(" UserId is set.");
        } else {
            System.out.println(" UserId is not set yet.\n");
            if ("".equals(language)) {
                return "index";
            }
            lc = new LanguagesController(language);
            if ("".equals(gender)) {
                map.put("language", language);
                map.put("genderQuestion", lc.getText("GenderQuestion"));
                map.put("male", lc.getText("Male"));
                map.put("female", lc.getText("Female"));
                return "gender";
            }

            if ("".equals(ageGroup)) {
                map.put("language", language);
                map.put("gender", gender);
                map.put("ageGroupQuestion", lc.getText("AgeGroupQuestion"));
                map.put("younger", lc.getText("Younger"));
                map.put("older", lc.getText("Older"));
                return "agegroup";
            }

            //TODO create id
            gss = new DatabaseStorage();
            gss.setGender(gender);
            gss.setAgeGroup(ageGroup);
            userId = gss.getUserId() + "";

            gss.saveStatistics();

        }
        if (!"".equals(random) && IntegerChecker.isInteger(random)) {
            System.out.println(" Random is set.");
            newRandom = Long.parseLong(random);
        } else {
            System.out.println(" Random was not set");
            newRandom = System.currentTimeMillis();
        }

        System.out.println(" Retrieving question...");
        String xmlFileName = "questions_en.xml";
        if (!"".equals(language)) {
            lc = new LanguagesController(language);
            switch (language.toLowerCase()) {
                case "nederlands":
                    xmlFileName = "questions_nl.xml";
                    break;
                case "english":
                    xmlFileName = "questions_en.xml";
                    break;
            }
        } else {
            lc = new LanguagesController("English");
        }
        QuestionPool qp = new QuestionPool(xmlFileName);
        int newQuestionNr = 0;
        if (!"".equals(questionNr) && IntegerChecker.isInteger(questionNr)) {
            newQuestionNr = Integer.parseInt(questionNr);
            System.out.println("  Question is set.");
        } else {
            System.out.println("  No question set: assuming first question.");
        }
        List<Question> questions = qp.getQuestions();
        Collections.shuffle(questions, new Random(newRandom));
        if (newQuestionNr >= questions.size()) {
            map.put("endQuiz", lc.getText("EndQuiz"));
            map.put("restartQuiz", lc.getText("RestartQuiz"));
            return "endquiz";
        }
        Question q = questions.get(newQuestionNr);

        if (!"".equals(answer)) {
            Answer givenAnswer = Answer.fromString(answer);
            boolean right = givenAnswer != null && q.getRightAnswer().equalsAnswer(givenAnswer);
            System.out.println("Store result in database");
            gss.setScore(q.getId(), right);
            gss.saveStatistics();

            System.out.println(" Prepare explanation webview");
            map.put("explanation", q.getExplanation());
            if (right) {
                map.put("questionText", lc.getText("RightAnswer") + q.getRightAnswer().toString());
            } else {
                map.put("questionText", lc.getText("WrongAnswer") + q.getRightAnswer().toString());
            }
            map.put("userid", userId);
            map.put("random", newRandom + "");
            map.put("questionNr", newQuestionNr + 1 + "");
            map.put("language", language);
            map.put("imgName", q.getExplanationImage());
            map.put("nextQuestion", lc.getText("NextQuestion"));
            map.put("restartQuiz", lc.getText("RestartQuiz"));
            return "explanation";
        }

        System.out.println(" Prepare question in webview");
        map.put("question", q.getQuestion());
        map.put("answerA", q.getAnswerA());
        map.put("answerB", q.getAnswerB());
        map.put("answerC", q.getAnswerC());
        map.put("userid", userId);
        map.put("random", newRandom + "");
        map.put("questionNr", newQuestionNr + "");
        map.put("language", language);

        System.out.println("Request handled.\n");

        return "question";
    }
}
