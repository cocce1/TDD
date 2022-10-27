import org.example.InMemory;
import org.example.Questions;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;


public class InMemoryDeleteTest {

    InMemory memory;
    @BeforeEach
    public void setup(){
        memory = new InMemory();
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
}
