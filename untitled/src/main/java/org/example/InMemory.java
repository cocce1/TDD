package org.example;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemory implements QuestionRepo{

    private final Map<Integer,Questions> data = new HashMap<>();

    @Override
    public Optional<Questions> add(Questions questions) {
        return Optional.empty();
    }

    @Override
    public String delete(Integer id) {

        if(data.get(id) == null){
            return "can't find Question with id: " + id;
        }
        else {
            data.remove(id);
            return "question with id " + 1 + " was removed";
        }
    }

    @Override
    public Questions getQuestion(Integer id) {
        return null;
    }

    @Override
    public Collection<Questions> getAllQuestions() {
        return null;
    }

    @Override
    public String update(Integer id) {
        return null;
    }
}
