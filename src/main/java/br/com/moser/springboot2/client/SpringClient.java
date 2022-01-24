package br.com.moser.springboot2.client;

/**
 * @author Juliano Moser
 */

import br.com.moser.springboot2.domain.Vinyl;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {

        ResponseEntity<Vinyl> entity = new RestTemplate().getForEntity("http://localhost:8080/vinyls/{id}", Vinyl.class, 2);
        log.info(entity);

        Vinyl object = new RestTemplate().getForObject("http://localhost:8080/vinyls/{id}", Vinyl.class, 2);
        log.info(object);

        Vinyl[] vinyls = new RestTemplate().getForObject("http://localhost:8080/vinyls/all", Vinyl[].class);
        log.info(Arrays.toString(vinyls));

        ResponseEntity<List<Vinyl>> exchange = new RestTemplate().exchange("http://localhost:8080/vinyls/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});
        log.info(exchange.getBody());

//        Vinyl devinTownsend = Vinyl.builder().name("Devin Townsend").build();
//        Vinyl devinTownsendSaved = new RestTemplate().postForObject("http://localhost:8080/vinyls/", devinTownsend, Vinyl.class);
//        log.info("Saved vinyl {}", devinTownsendSaved);

        Vinyl jackGardiner = Vinyl.builder().name("Jack Gardiner").build();
        ResponseEntity<Vinyl> jackGardinerSaved = new RestTemplate().exchange("http://localhost:8080/vinyls/",
                HttpMethod.POST,
                new HttpEntity<>(jackGardiner, createJsonHeader()),
                Vinyl.class);
        log.info("Saved vinyl {}", jackGardinerSaved);

    }

    private static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
