package br.com.moser.springboot2.controller;

import br.com.moser.springboot2.domain.Vinyl;
import br.com.moser.springboot2.requests.VinylPostRequestBody;
import br.com.moser.springboot2.requests.VinylPutRequestBody;
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
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(vinylService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Vinyl> findById(@PathVariable long id) {
        return ResponseEntity.ok(vinylService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Vinyl>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(vinylService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Vinyl> save(@RequestBody VinylPostRequestBody vinylPostRequestBody) {
        return new ResponseEntity<>(vinylService.save(vinylPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        vinylService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    public ResponseEntity<Void> replace(@RequestBody VinylPutRequestBody vinylPutRequestBody) {
        vinylService.replace(vinylPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
