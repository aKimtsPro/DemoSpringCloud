package bstorm.akimts.film.config;

import feign.RetryableException;
import feign.Retryer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomRetryer implements Retryer {

    @Override
    public void continueOrPropagate(RetryableException e) {
        try{
            Thread.sleep(1000);
            log.info("retrying");
        } catch (InterruptedException interruptedException) {
            throw e;
        }
    }

    @Override
    public Retryer clone() {
        return new CustomRetryer();
    }
}
