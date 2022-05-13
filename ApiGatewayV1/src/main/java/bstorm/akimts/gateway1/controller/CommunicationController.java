package bstorm.akimts.gateway1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CommunicationController {

    private final RestTemplate restTemplate;

    public CommunicationController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/client/commu")
    public String fromClient(){
        return restTemplate.getForObject("http://client-service/commu", String.class);
    }

    @GetMapping("/film/commu")
    public String fromFilm(){
        return restTemplate.getForObject("http://film-service/commu", String.class);
    }
}
