import org.example.InMemory;
import org.example.QuestionRepo;
import org.example.Questions;
import org.example.exception.NotExistException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;


public class InMemoryDeleteTest {

    QuestionRepo repo;

    @BeforeEach
    public void setup(){
        repo = new InMemory();

    }

    @Test
    @DisplayName("Remove 1 question from memory get right message")
    void remove_1_question(){
        Questions questions = new Questions(1,"Hallo",new String[]{"1","2","3"},"4");
        repo.add(questions);
        String result =  repo.delete(questions.getId());

        String expected = "question with id " + 1 + " was removed";

    Assertions.assertEquals(expected,result);
}

    @Test
    @DisplayName("Delete question from storage")
    void delete_question_from_memory(){
        Questions questions = new Questions(1,"Hallo",new String[]{"1","2","3"},"4");
        repo.add(questions);
        Assertions.assertDoesNotThrow(() -> repo.delete(questions.getId()));
        Assertions.assertEquals(0,repo.count());
    }

   @Test
    @DisplayName("Error message if there is no question with that id")
    void error_message_if_no_match(){
       String result =  repo.delete(1);
       String expected = "can't find Question with id: " + 1;
       Assertions.assertEquals(expected,result);
   }

   @Test
   @DisplayName("Delete null  throw Null pointer exception")
   void delete_null_question_throw_null_pointer(){
       Questions questions = new Questions(1,"Hallo",new String[]{"1","2","3"},"4");
       repo.add(questions);
       Assertions.assertThrows(NullPointerException.class,() ->repo.delete(null));
   }
   @Test
   @DisplayName("delete throw not exist exception")
   void delete_throw_not_exist_exception(){
       Questions questions = new Questions(1,"Hallo",new String[]{"1","2","3"},"4");
       repo.add(questions);
       Assertions.assertThrows(NotExistException.class,() -> repo.delete(25));
       Assertions.assertEquals(1,repo.count());
   }
   @ParameterizedTest
   @MethodSource("multiQuestion")
    @DisplayName("Remove question in collection")
    void memory_should_be_one_less(Questions questions){
       repo.add(questions);
       repo.delete(2);
       Assertions.assertEquals(3,repo.count());
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
