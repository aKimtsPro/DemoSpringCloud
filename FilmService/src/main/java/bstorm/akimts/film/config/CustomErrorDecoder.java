package bstorm.akimts.film.config;

import bstorm.akimts.film.exceptions.BadRequestException;
import bstorm.akimts.film.exceptions.NotFoundException;
import bstorm.akimts.film.exceptions.ServiceUnreachableException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

import java.util.Date;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String exMessage, Response response) {
        return switch (response.status()) {
            case 400 -> new BadRequestException();
            case 404 -> new NotFoundException();
            default -> new RetryableException(
                    response.status(),
                    "Retry this",
                    response.request().httpMethod(),
                    new ServiceUnreachableException(),
                    new Date(),
                    response.request());
        };
    }
}
