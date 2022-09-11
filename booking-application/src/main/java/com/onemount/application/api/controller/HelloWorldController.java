package com.onemount.application.api.controller;

import com.onemount.application.api.response.dto.HelloDto;
import com.onemount.application.api.response.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Hello World APIs
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping
public class HelloWorldController {
    @GetMapping("/messages/{id}")
    public BaseResponse<HelloDto> sayHelloWorld(@PathVariable String id) {
        var res = HelloDto.builder()
                .id(id)
                .message("Hello, it works!")
                .build();
        return BaseResponse.ofSucceeded(res);
    }
}