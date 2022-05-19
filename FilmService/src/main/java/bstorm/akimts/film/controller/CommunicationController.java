package bstorm.akimts.film.controller;

import bstorm.akimts.film.clients.ClientClient;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/commu")
public class CommunicationController {

    private final ClientClient client;

    public CommunicationController(ClientClient client) {
        this.client = client;
    }

    @GetMapping
    public String textCommu(){
        log.info("GET - /commu");
        return "communication réussie";
    }

    @GetMapping("/error")
    public void getError(){
        client.getError();
    }

    @GetMapping("/not_found")
    public void getNotFound(){
        client.getNotFound();
    }

    @GetMapping("/to_client")
    public String getCommu(){
        return "from client : " + client.startCom();
    }

    @GetMapping("/config")
    public String getFromConfig(@Value("${spring.cloud.consul.host}") String value){
        return value;
    }
}
