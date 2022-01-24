package br.com.moser.springboot2.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Juliano Moser
 */
@Data
public class VinylPostRequestBody {

    @NotEmpty(message = "The vinyl name cannot be empty")
    private String name;
}
