package be.pxl.olympicgames.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OlympicGamesController {

	// implement the endpoints here

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
