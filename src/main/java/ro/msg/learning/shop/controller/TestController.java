package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.service.TestService;

@RestController
@Profile("test")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @GetMapping(value = "/test/populate")
    public void populate() {
        testService.populate();
    }

    @GetMapping(value = "/test/clear")
    public void clear() {
        testService.clear();
    }
}
