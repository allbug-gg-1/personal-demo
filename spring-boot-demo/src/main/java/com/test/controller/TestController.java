package com.test.controller;

import com.test.handler.WebResponse;
import com.test.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/test")
    public WebResponse test() {
        WebResponse response = new WebResponse();
        response.setBody(testService.test());
        return response;
    }
}
