package bstorm.akimts.gateway2.config;

import bstorm.akimts.gateway2.predicate.NumberOfParamsRoutePredicateFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RouteLocatorConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    //@Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder, NumberOfParamsRoutePredicateFactory pf){
        return builder.routes()
                .route("to-client",
                        r -> r.path("/client/**")
                                .and()
                                .method("GET")
                                .filters(f ->
                                        f.rewritePath("/client/(?<path>.*)", "${path}")
                                                .addRequestParameter("mon-param", "sa-valeur")
                                                .retry(3)
                                )
                                .uri("lb://client-service")
                )
                .route("to-film",
                        r -> r.path("/film/**")
                                .filters(f -> f.rewritePath("/film/(?<path>.*)", "${path}"))
                                .uri("lb://film-service")
                )
                .route("mot",
                        r -> r.path("/mot")
                                .and()
                                .method("GET")
                                .and()
                                .predicate(pf.apply( new NumberOfParamsRoutePredicateFactory.Config(0) ))
//                                .or()
//                                .predicate((exchange) -> Objects.equals(exchange.getRequest().getQueryParams().get("mot").get(0), "gateway"))
                                .filters( ( req ) -> req.addRequestParameter("mot", "gateway"))
                                .uri("lb://film-service"))
                .build();
    }

}
