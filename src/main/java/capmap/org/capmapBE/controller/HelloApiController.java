package capmap.org.capmapBE.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloApiController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }
}