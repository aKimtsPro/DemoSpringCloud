package bstorm.akimts.client.clients;

import bstorm.akimts.client.config.FeignConfig;
import bstorm.akimts.user.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "user-service", configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping(path = "/login",headers = "Authorization")
    public UserDTO validateToken(@RequestHeader("Authorization")String authHeader);

}
