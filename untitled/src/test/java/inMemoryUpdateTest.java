import org.example.QuestionRepo;
import org.example.exception.FieldEmptyException;
import org.example.InMemory;
import org.example.Questions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class inMemoryUpdateTest {

    QuestionRepo memory;

    @BeforeEach
    public void setup(){
        memory = new InMemory();

    }

@Test
    @DisplayName("update question")
    public void update_question(){
    Questions questions = new Questions(1,"Hallo",new String[]{"1","2","3"},"4");
    memory.add(questions);
    Questions result = memory.update(questions.getId(),"nej",new String[]{"1","2","6"},"5");
    Questions expected = new Questions(1,"nej",new String[]{"1","2","6"},"5");
    Assertions.assertEquals(expected,result);
}

    @Test
    @DisplayName("question didn't update")
    public void question_not_update(){
        Questions questions = new Questions(1,"Hallo",new String[]{"1","2","3"},"4");

        Questions result = memory.update(questions.getId(),"nej",new String[]{"1","2","6"},"5");
        Questions expected = new Questions(1,"nej",new String[]{"1","2","6"},"5");
        Assertions.assertNotEquals(expected,result);
    }

    @Test
    @DisplayName("Answer not null")
    public void answer_null_throw_exception(){
        Questions questions = new Questions(1,"Hallo",new String[]{"1","2","3"},"4");
        memory.add(questions);
    Assertions.assertThrows(NullPointerException.class, () -> memory.update(1,"hallo",null,"4"));
}

    @Test
    @DisplayName("Question not null")
    public void question_null_throw_exception(){
        Questions questions = new Questions(1,"Hallo",new String[]{"1","2","3"},"4");
        memory.add(questions);
        Assertions.assertThrows(NullPointerException.class, () -> memory.update(1,null,new String[]{"1","2","3"},"4"));
    }

    @Test
    @DisplayName("correct answer not null")
    public void correct_null_throw_exception(){
        Questions questions = new Questions(1,"Hallo",new String[]{"1","2","3"},"4");
        memory.add(questions);
        Assertions.assertThrows(NullPointerException.class, () -> memory.update(1,"hallo",new String[]{"1","2","3"},null));
    }

    @Test
    @DisplayName("Answer not empty")
    public void answer_not_empty(){
        Questions questions = new Questions(1,"Hallo",new String[]{"1","2","3"},"4");
        memory.add(questions);
        Assertions.assertThrows(FieldEmptyException.class, () -> memory.update(1,"hallo",new String[]{},"4"));
    }

    @Test
    @DisplayName("Question not null")
    public void question_null_throw_empty(){
        Questions questions = new Questions(1,"Hallo",new String[]{"1","2","3"},"4");
        memory.add(questions);
        Assertions.assertThrows(FieldEmptyException.class, () -> memory.update(1," ",new String[]{"1","2","3"},"4"));
    }

    @Test
    @DisplayName("correct answer not null")
    public void correct_null_throw_empty(){
        Questions questions = new Questions(1,"Hallo",new String[]{"1","2","3"},"4");
        memory.add(questions);
        Assertions.assertThrows(FieldEmptyException.class, () -> memory.update(1,"hallo",new String[]{"1","2","3"}," "));
    }
}
