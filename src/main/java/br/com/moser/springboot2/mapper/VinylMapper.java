package br.com.moser.springboot2.mapper;

import br.com.moser.springboot2.domain.Vinyl;
import br.com.moser.springboot2.requests.VinylPostRequestBody;
import br.com.moser.springboot2.requests.VinylPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Juliano Moser
 */
@Mapper(componentModel = "spring")
public abstract class VinylMapper {

    public static final VinylMapper INSTANCE = Mappers.getMapper(VinylMapper.class);

    public abstract Vinyl toVinyl(VinylPostRequestBody vinylPostRequestBody);

    public abstract Vinyl toVinyl(VinylPutRequestBody vinylPutRequestBody);
}
