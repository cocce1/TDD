import org.example.InMemory;
import org.example.Questions;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class InMemoryDeleteTest {

    InMemory memory;
    Map<Integer,Questions> questionsMap;
    @BeforeEach
    public void setup(){
        memory = new InMemory();
        questionsMap =  new HashMap<>();
    }

    @Test
    @DisplayName("Remove 1 question from memory")
    void remove_1_question(){
        Questions questions = new Questions(1,"Hallo",new String[]{"1","2","3"},"4");
        InMemory mock = Mockito.mock(InMemory.class);
        Mockito.when(mock.getQuestion(questions.getId())).thenReturn(questions);
      String result =  memory.delete(questions.getId());

      String expected = "question with id " + 1 + " was removed";

    Assertions.assertEquals(expected,result);
}

   @Test
    @DisplayName("Error message if there is no question with that id")
    void error_message_if_no_match(){
       Questions questions = new Questions(1,"Hallo",new String[]{"1","2","3"},"4");
       InMemory mock = Mockito.mock(InMemory.class);
       Mockito.when(mock.getQuestion(questions.getId())).thenReturn(questions);
       String result =  memory.delete(questions.getId());

       String expected = "can't find Question with id: " + 1;

       Assertions.assertEquals(expected,result);
   }
}
