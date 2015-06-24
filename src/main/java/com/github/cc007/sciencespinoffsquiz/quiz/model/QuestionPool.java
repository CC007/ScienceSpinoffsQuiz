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
package com.github.cc007.sciencespinoffsquiz.quiz.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Rik Schaaf aka CC007 <http://coolcat007.nl/>
 */
public class QuestionPool {

    List<Question> questions;

    public QuestionPool(String xmlFileName) {
        try {
            this.questions = new ArrayList<>();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(this.getClass().getClassLoader().getResource("") + xmlFileName);
            NodeList nList = ((Element) doc.getElementsByTagName("questions").item(0)).getElementsByTagName("question");

            for (int i = 0; i < nList.getLength(); i++) {
                Node n = nList.item(i);

                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) n;
                    Answer rightAnswer = Answer.fromString(e.getElementsByTagName("rightanswer").item(0).getTextContent());
                    if (rightAnswer == null) {
                        System.out.println("The inputfile constains an illegal rightanswer node (question id = " + e.getAttribute("id") + "), value: \"" + e.getElementsByTagName("rightanswer").item(0).getTextContent() + "\"");
                        System.exit(-1);
                    }
                    Question q = new Question(
                            Integer.parseInt(e.getAttribute("id")),
                            e.getElementsByTagName("text").item(0).getTextContent(),
                            e.getElementsByTagName("answerA").item(0).getTextContent(),
                            e.getElementsByTagName("answerB").item(0).getTextContent(),
                            e.getElementsByTagName("answerC").item(0).getTextContent(),
                            rightAnswer,
                            e.getElementsByTagName("explanation").item(0).getTextContent(),
                            e.getElementsByTagName("explanationimage").item(0).getTextContent(),
                            e.getElementsByTagName("keyword").item(0).getTextContent());
                    questions.add(q);

                }
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(QuestionPool.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(QuestionPool.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(QuestionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Question> getQuestions() {
        return questions;
    }

}
