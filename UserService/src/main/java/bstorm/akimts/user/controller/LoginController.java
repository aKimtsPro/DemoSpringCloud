package bstorm.akimts.user.controller;

import bstorm.akimts.user.dto.LoginForm;
import bstorm.akimts.user.dto.UserDTO;
import bstorm.akimts.user.service.LoginService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService service;

    public LoginController(LoginService service) {
        this.service = service;
    }

    @PostMapping
    public String login(@RequestBody LoginForm form){
        return service.login(form);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(name="",headers = "Authorization")
    public UserDTO validate(Authentication auth){
        return new UserDTO(
                (String)auth.getPrincipal(),
                null,
                auth.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList()
        );
    }

}
