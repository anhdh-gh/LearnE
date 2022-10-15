package source.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import source.dto.response.BaseResponse;

@RestController
public class HealthCheck {

    @GetMapping("/health-check")
    public BaseResponse healthCheck() {
        return BaseResponse.ofSucceeded("LearnE gateway service is running!");
    }
}
