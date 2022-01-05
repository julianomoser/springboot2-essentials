package br.com.moser.springboot2.service;

import br.com.moser.springboot2.domain.Vinyl;
import br.com.moser.springboot2.mapper.VinylMapper;
import br.com.moser.springboot2.reposiitory.VinylRepository;
import br.com.moser.springboot2.requests.VinylPostRequestBody;
import br.com.moser.springboot2.requests.VinylPutRequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author Juliano Moser
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VinylService {

    private final VinylRepository vinylRepository;

    public List<Vinyl> listAll() {
        log.info("Listing all vinyl's");
        return vinylRepository.findAll();
    }

    public Vinyl findByIdOrThrowBadRequestException(long id) {
        log.info("Retrieve vinyl by ID '{}'", id);
        return vinylRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vinyl not Found"));
    }

    public Vinyl save(VinylPostRequestBody vinylPostRequestBody) {
        log.info("Saving a new vinyl");
        return vinylRepository.save(VinylMapper.INSTANCE.toVinyl(vinylPostRequestBody));
    }

    public void delete(long id) {
        log.info("Deleting vinyl by ID '{}'", id);
        vinylRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(VinylPutRequestBody vinylPutRequestBody) {
        Vinyl savedVinyl = findByIdOrThrowBadRequestException(vinylPutRequestBody.getId());
        log.info("Replacing vinyl by ID '{}' ", savedVinyl.getId());
        Vinyl vinyl = VinylMapper.INSTANCE.toVinyl(vinylPutRequestBody);
        vinyl.setId(savedVinyl.getId());
        vinylRepository.save(vinyl);
    }
}
