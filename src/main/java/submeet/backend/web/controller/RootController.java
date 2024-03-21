package submeet.backend.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/health")
    public String healthCheck(){
        return "I'm healthy";
    }

    @GetMapping("/member")
    public String member(){
        return "hi";
    }
}