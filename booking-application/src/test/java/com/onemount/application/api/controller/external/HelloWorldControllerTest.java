package com.onemount.application.api.controller.external;

import com.onemount.application.api.controller.HelloWorldController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(properties = "spring.flyway.enabled=false")
class HelloWorldControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private HelloWorldController helloWorldController;
    private static final String SAY_HELLO_API = "/messages/{id}";

    @Test
    void test_sayHelloWorld_should_success() throws Exception {
        assertNotNull(helloWorldController);

        var id = UUID.randomUUID().toString();
        mvc.perform(get(SAY_HELLO_API, id)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(id))
                .andExpect(jsonPath("$.data.message").value("Hello, it works!"));
    }
}