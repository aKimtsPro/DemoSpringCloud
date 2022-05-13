package bstorm.akimts.film.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/commu")
public class CommunicationController {

    @GetMapping
    public String textCommu(){
        log.info("GET - /commu");
        return "communication réussie";
    }

}
