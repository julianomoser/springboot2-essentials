package br.com.moser.springboot2.controller;

import br.com.moser.springboot2.domain.Vinyl;
import br.com.moser.springboot2.service.VinylService;
import br.com.moser.springboot2.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Juliano Moser
 */
@RestController
@RequestMapping("vinyls")
@Slf4j
@RequiredArgsConstructor()
public class VinylController {

    private final DateUtil dateUtil;
    private final VinylService vinylService;

    @GetMapping
    public ResponseEntity<List<Vinyl>> list() {
        log.info("Listing all vinyl's '{}'", dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(vinylService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Vinyl> findById(@PathVariable long id) {
        log.info("Retrieve vinyl by id '{}'", id);
        return ResponseEntity.ok(vinylService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Vinyl> save(@RequestBody Vinyl vinyl) {
        log.info("Saving a new vinyl");
        return new ResponseEntity<>(vinylService.save(vinyl), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        log.info("Deleting vinyl by id '{}'", id);
        vinylService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    public ResponseEntity<Void> replace(@RequestBody Vinyl vinyl) {
        log.info("Replace vinyl by id '{}'", vinyl.getId());
        vinylService.replace(vinyl);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
