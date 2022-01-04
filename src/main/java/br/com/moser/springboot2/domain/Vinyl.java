package br.com.moser.springboot2.domain;

import br.com.moser.springboot2.service.VinylService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author Juliano Moser
 */
@Data
@ToString
@AllArgsConstructor
public class Vinyl {

    private Long id;
    private String name;
}
