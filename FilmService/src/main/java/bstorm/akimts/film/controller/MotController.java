package bstorm.akimts.film.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mot")
public class MotController {

    @PreAuthorize("isAuthenticated()")
    @GetMapping(params = "mot")
    public String getMot(@RequestParam String mot){
        return "votre mot est: "+mot;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public String getNomUser(Authentication auth){
        return "votre nom est: " + auth.getPrincipal();
    }

}
