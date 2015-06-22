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
package com.github.cc007.sciencespinoffsquiz.language;

import com.github.fge.msgsimple.bundle.MessageBundle;
import com.github.fge.msgsimple.provider.MessageSourceProvider;
import com.github.fge.msgsimple.provider.StaticMessageSourceProvider;
import com.github.fge.msgsimple.source.MapMessageSource;
import com.github.fge.msgsimple.source.MessageSource;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author Rik Schaaf aka CC007 <http://coolcat007.nl/>
 */
public class LanguagesController {

    private final MessageBundle translation;
    private final Locale language;

    public LanguagesController(String language) {
        Locale dutch = new Locale("nl", "NL");
        Map<String, Locale> supportedLanguages = new HashMap<>();
        supportedLanguages.put("Nederlands", dutch);
        supportedLanguages.put("English", Locale.ENGLISH);
        this.language = supportedLanguages.get(language);
            //PropertiesMessageSource.fromFile(new File(this.getClass().getClassLoader().getResource("language_en.properties").toString()));
        //PropertiesMessageSource.fromFile(new File(this.getClass().getClassLoader().getResource("language_nl.properties").toString()));
        MessageSource sourceEN = MapMessageSource.newBuilder()
                .put("GenderQuestion", "Are you male or female?")
                .put("Male", "Male")
                .put("Female", "Female")
                .put("AgeGroupQuestion", "Are you younger or older than 30 years old?")
                .put("Younger", "Younger than 30 years old")
                .put("Older", "Older than 30 years old")
                .put("RightAnswer", "Correct! The answer was indeed ")
                .put("WrongAnswer", "Wrong! The answer is ")
                .put("NextQuestion", "Next question")
                .put("EndQuiz", "This is the end of the quiz! Thanks for participating!")
                .put("RestartQuiz", "Restart quiz")
                .build();

        MessageSource sourceNL = MapMessageSource.newBuilder()
                .put("GenderQuestion", "Bent u een man of een vrouw?")
                .put("Male", "Man")
                .put("Female", "Vrouw")
                .put("AgeGroupQuestion", "Bent u jonger of ouder dan 30 jaar?")
                .put("Younger", "Jonger dan 30 jaar")
                .put("Older", "Ouder dan 30 jaar")
                .put("RightAnswer", "Goed!<br /> Het antwoord was inderdaad ")
                .put("WrongAnswer", "Fout!<br /> Het goede antwoord is  ")
                .put("NextQuestion", "Volgende vraag")
                .put("EndQuiz", "Dit is het einde van de quiz! Bedankt voor uw deelname!")
                .put("RestartQuiz", "Herstart quiz")
                .build();
        MessageSourceProvider provider = StaticMessageSourceProvider.newBuilder().addSource(dutch, sourceNL).addSource(Locale.ENGLISH, sourceEN).build();
        translation = MessageBundle.newBuilder().appendProvider(provider).freeze();
    }

    public String getText(String keyword) {
        return translation.getMessage(language, keyword);
    }

}
