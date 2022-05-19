package bstorm.akimts.client.config;

import bstorm.akimts.client.clients.UserClient;
import bstorm.akimts.user.dto.UserDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtValidationFilter extends OncePerRequestFilter {

//    private final RestTemplate restTemplate;
    private final UserClient client;

    public JwtValidationFilter(/*RestTemplate restTemplate, */UserClient client) {
//        this.restTemplate = restTemplate;
        this.client = client;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if( token != null ) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", token);

            HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

            try {

                UserDTO dto = client.validateToken(token);
                Authentication auth = new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        null,
                        dto.getRoles().stream()
                                .map(SimpleGrantedAuthority::new)
                                .toList()
                );
//                ResponseEntity<UserDTO> responseEntity = restTemplate.exchange(
//                        "http://user-service/login",
//                        HttpMethod.GET,
//                        httpEntity,
//                        UserDTO.class
//                );
//
//                if (responseEntity.getStatusCode().is2xxSuccessful()) {
//
//                    UserDTO user = responseEntity.getBody();
//                    Authentication auth = new UsernamePasswordAuthenticationToken(
//                            user.getUsername(),
//                            null,
//                            user.getRoles().stream()
//                                    .map(SimpleGrantedAuthority::new)
//                                    .toList()
//                    );
                    SecurityContextHolder.getContext().setAuthentication(auth);
//                }
            }
            catch (Exception ignored){}

        }
        filterChain.doFilter(request,response);

    }
}
