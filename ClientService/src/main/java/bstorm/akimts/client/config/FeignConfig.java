package bstorm.akimts.client.config;

import feign.Logger;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

//    @Bean
    public Logger.Level logger(){
        return Logger.Level.BASIC;
    }

}
