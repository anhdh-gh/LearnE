package source;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:error.properties", encoding = "UTF-8")
public class LearnEApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnEApplication.class, args);
    }
}