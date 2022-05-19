package bstorm.akimts.film.clients;

import bstorm.akimts.user.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "user-service")
public interface UserClient {

    @GetMapping(path = "/login",headers = "Authorization")
    public UserDTO validateToken(@RequestHeader("Authorization")String authHeader);

}
