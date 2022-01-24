package br.com.moser.springboot2.service;

import br.com.moser.springboot2.domain.Vinyl;
import br.com.moser.springboot2.exception.BadRequestException;
import br.com.moser.springboot2.mapper.VinylMapper;
import br.com.moser.springboot2.reposiitory.VinylRepository;
import br.com.moser.springboot2.requests.VinylPostRequestBody;
import br.com.moser.springboot2.requests.VinylPutRequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public Page<Vinyl> listAll(Pageable pageable) {
        return vinylRepository.findAll(pageable);
    }

    public List<Vinyl> findByName(String name) {
        return vinylRepository.findByName(name);
    }

    public Vinyl findByIdOrThrowBadRequestException(long id) {
        return vinylRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Vinyl not Found"));
    }

    @Transactional
    public Vinyl save(VinylPostRequestBody vinylPostRequestBody) {
        return vinylRepository.save(VinylMapper.INSTANCE.toVinyl(vinylPostRequestBody));
    }

    public void delete(long id) {
        vinylRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(VinylPutRequestBody vinylPutRequestBody) {
        Vinyl savedVinyl = findByIdOrThrowBadRequestException(vinylPutRequestBody.getId());
        Vinyl vinyl = VinylMapper.INSTANCE.toVinyl(vinylPutRequestBody);
        vinyl.setId(savedVinyl.getId());
        vinylRepository.save(vinyl);
    }
}
