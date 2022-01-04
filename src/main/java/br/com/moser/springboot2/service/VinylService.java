package br.com.moser.springboot2.service;

import br.com.moser.springboot2.domain.Vinyl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Juliano Moser
 */
@Service
public class VinylService {

    private static List<Vinyl> vinyls;

    static {
        vinyls = new ArrayList<>(List.of(new Vinyl(1L, "Intervals"), new Vinyl(2L, "Plini")));
    }

    // private final VinylRepository vinylRepository;

    public List<Vinyl> listAll() {
        return vinyls;
    }

    public Vinyl findById(long id) {
        return vinyls.stream()
                .filter(vinyl -> vinyl.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vinyl not Found"));
    }

    public Vinyl save(Vinyl vinyl) {
        vinyl.setId(ThreadLocalRandom.current().nextLong(3, 100000));
        vinyls.add(vinyl);
        return vinyl;
    }

    public void delete(long id) {
        vinyls.remove(findById(id));
    }

    public void replace(Vinyl vinyl) {
        delete(vinyl.getId());
        vinyls.add(vinyl);
    }
}
