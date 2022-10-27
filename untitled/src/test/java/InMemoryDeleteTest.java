import org.example.InMemory;
import org.example.Questions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;


public class InMemoryDeleteTest {

    InMemory memory;
    Map<Integer,Questions> questionsMap;
    @BeforeEach
    public void setup(){
        memory = new InMemory();
        questionsMap = new HashMap<>();
    }

    @Test
    @DisplayName("Remove 1 question from memory")
    void remove_1_question(){
        Questions questions = new Questions(1,"Hallo",new String[]{"1","2","3"},"4");
        InMemory mock = Mockito.mock(InMemory.class);
        Mockito.when(mock.add(questions)).thenReturn(Optional.of(questions));
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
       String result =  mock.delete(questions.getId());

       String expected = "can't find Question with id: " + 1;

       Assertions.assertEquals(expected,result);
   }

   @ParameterizedTest
   @MethodSource("multiQuestion")
    @DisplayName("Map should me one less")
    void memory_should_be_one_less(Questions questions){
       InMemory mock = Mockito.mock(InMemory.class);
       Mockito.when(mock.add(questions)).thenReturn(Optional.of(questions));
       Mockito.when(mock.getAllQuestions()).thenReturn(List.of(questions));
       memory.add(questions);
       List<Questions>result = (List<Questions>) memory.getAllQuestions();
       memory.delete(2);
       Assertions.assertEquals(3,result.size());

    }

    public static Stream<Arguments> multiQuestion(){
        return Stream.of(
                        Arguments.arguments(new Questions(1,"Hallo",new String[]{"1","2","3"},"4"),
                        Arguments.arguments(new Questions(2,"Hallo",new String[]{"1","2","3"},"4")),
                        Arguments.arguments(new Questions(3,"Hallo",new String[]{"1","2","3"},"4")),
                        Arguments.arguments(new Questions(4,"Hallo",new String[]{"1","2","3"},"4")),
                        Arguments.arguments(new Questions(5,"Hallo",new String[]{"1","2","3"},"4")))
        );
    }
}
