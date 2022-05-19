package bstorm.akimts.film;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FilmApp {

    public static void main(String[] args) {
        SpringApplication.run(FilmApp.class, args);
    }

}
