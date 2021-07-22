package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.service.TestService;

@RestController
@Profile("test")
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping(value = "/populate")
    public void populate() {
        testService.populate();
    }

    @GetMapping(value = "/clear")
    public void clear() {
        testService.clear();
    }
}
