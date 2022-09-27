package source;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@PropertySource(value = "classpath:error.properties", encoding = "UTF-8")
public class LearnEApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnEApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder getByBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}



