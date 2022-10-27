package org.example;

import java.util.Collection;
import java.util.Optional;

public interface QuestionRepo {

    Optional<Questions> add(Questions questions);
    String delete(Integer id);
    Questions getQuestion(Integer id);
    Collection<Questions> getAllQuestions();
    Questions update(Integer id,String question, String[] answers,String correctAnswer);

}
