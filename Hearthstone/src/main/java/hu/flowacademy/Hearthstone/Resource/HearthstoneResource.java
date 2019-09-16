package hu.flowacademy.Hearthstone.Resource;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api")
public class HearthstoneResource {

/*    @GetMapping("/hello")
    public List<String> hello() {
        return List.of("hello");
    }*/

    @MessageMapping("/hello")
    // @SendTo("/greetings")
    public List<String> greeting(String message) throws Exception {
        System.out.println(message);
        Thread.sleep(1000);
        return List.of(message);
    }
}
