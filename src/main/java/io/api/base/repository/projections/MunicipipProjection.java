package io.api.base.repository.projections;

import io.api.base.domain.Departamento;
import io.api.base.domain.Municipio;
import io.api.base.domain.Pais;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlineMunicipio", types = {Municipio.class})
public interface MunicipipProjection {

    String getNombre();

    Long getId();

    Boolean getActivo();

    Departamento getDepartamento();

    @Value("#{target.getDepartamento().getPais()}")
    Pais getPais();

}
