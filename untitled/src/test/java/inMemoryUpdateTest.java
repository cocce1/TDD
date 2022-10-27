import org.example.InMemory;
import org.example.Questions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

public class inMemoryUpdateTest {

    InMemory memory;
    Map<Integer, Questions> questionsMap;
    @BeforeEach
    public void setup(){
        memory = new InMemory();
        questionsMap = new HashMap<>();
    }

@Test
    @DisplayName("update question")
    public void update_question(){
    Questions questions = new Questions(1,"Hallo",new String[]{"1","2","3"},"4");
    InMemory mock = Mockito.mock(InMemory.class);
    Mockito.when(mock.getQuestion(questions.getId())).thenReturn(questions);
    Questions result = mock.update(questions.getId(),"nej",new String[]{"1","2","6"},"5");
    Questions expected = new Questions(1,"nej",new String[]{"1","2","6"},"5");
    Assertions.assertEquals(expected,result);

}
}
