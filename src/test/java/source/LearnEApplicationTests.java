package source;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.format.DateTimeFormatter;
import java.util.Date;

@SpringBootTest
class LearnEApplicationTests {

    @Test
    void contextLoads() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Date date = new Date();
    }

}
