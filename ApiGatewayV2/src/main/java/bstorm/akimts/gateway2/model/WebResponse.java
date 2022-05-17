package bstorm.akimts.gateway2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebResponse<T> {

    private String message;
    private HttpStatus status;
    private Mono<T> body;

}
