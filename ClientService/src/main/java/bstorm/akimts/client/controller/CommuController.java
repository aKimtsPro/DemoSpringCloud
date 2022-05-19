package bstorm.akimts.client.controller;

import lombok.extern.slf4j.Slf4j;
// import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

// import java.net.URI;
// import java.util.List;
// import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/commu")
public class CommuController {

    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;

    public CommuController(DiscoveryClient discoveryClient, RestTemplate restTemplate) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String startCommu(){
        // throw new RuntimeException();
       String responseBody = restTemplate.getForObject("http://film-service/commu", String.class);
       return "GET to film-service \nresponse: " + responseBody;

//        List<ServiceInstance> instances = discoveryClient.getInstances("film-service");
//
//        ServiceInstance instance =  instances
//                .get( new Random().nextInt(instances.size()) );
//
//        URI uri = instance
//                .getUri()
//                .resolve("/commu");
//
//        String responseBody = restTemplate.getForObject(uri,String.class);
//        return "GET to film-service:"+ instance.getInstanceId() + " \nresponse: " + responseBody;

    }


    @GetMapping("/error")
    public void error(){
        log.error("Shit happened");
        throw new RuntimeException();
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @GetMapping("/not_found")
    public void notFound(){
        log.error("Shit happened");
    }

    @GetMapping("/slow")
    public void slowRequest(){
        log.info("sloooooooooooooooooooooow");
        try {
            Thread.sleep(10000);
        }
        catch (InterruptedException ex){
            log.error("interrupt");
        }

    }

}
