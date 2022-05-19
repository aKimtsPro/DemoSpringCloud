package bstorm.akimts.film.clients;

// import bstorm.akimts.film.config.BeanConfig;
import bstorm.akimts.film.exceptions.NotFoundException;
// import bstorm.akimts.film.exceptions.ServiceUnreachableException;
// import feign.Retryer;
// import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(
        name = "client-service" // ,
//        configuration = BeanConfig.class
        , fallback = ClientClient.Fallback.class
)

public interface ClientClient {

    @GetMapping("/commu/error")
    public void getError();

    @GetMapping("/commu/not_found")
    public void getNotFound();

    @GetMapping("/commu")
    String startCom();

    @Component
    class Fallback implements ClientClient{

        @Override
        public void getError() {
            System.out.println("on passe par ici");
        }

        @Override
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public void getNotFound() {
            throw new NotFoundException();
        }

        @Override
        public String startCom() {
            return "pas d'acces au service";
        }
    }

}
