package source.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class GetMessageUtils {

    @Autowired
    private Environment environment;

    public String getMessage(String code) {
        return environment.getProperty(code);
    }
}
