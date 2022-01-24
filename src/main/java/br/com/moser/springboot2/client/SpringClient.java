package br.com.moser.springboot2.client;

/**
 * @author Juliano Moser
 */

import br.com.moser.springboot2.domain.Vinyl;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {

        ResponseEntity<Vinyl> entity = new RestTemplate().getForEntity("http://localhost:8080/vinyls/{id}", Vinyl.class, 2);
        log.info(entity);

        Vinyl object = new RestTemplate().getForObject("http://localhost:8080/vinyls/{id}", Vinyl.class, 2);

        log.info(object);
    }
}
