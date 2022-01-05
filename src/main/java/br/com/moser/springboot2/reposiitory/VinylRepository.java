package br.com.moser.springboot2.reposiitory;

import br.com.moser.springboot2.domain.Vinyl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Juliano Moser
 */
@Repository
public interface VinylRepository extends JpaRepository<Vinyl, Long> {
}
