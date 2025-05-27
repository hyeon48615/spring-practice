package net.fullstack.api.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/sample2")
public class SampleRestController {
    @GetMapping("/test2")
    public String[] hello2() {
        return new String[] { "Hello", "Spring", "Boot" };
    }
}