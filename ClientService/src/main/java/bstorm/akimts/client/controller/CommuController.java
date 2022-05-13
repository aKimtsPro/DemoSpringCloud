package bstorm.akimts.client.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Random;

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
}
