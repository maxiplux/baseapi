package io.api.base.controllers.generics;


import io.api.base.domain.Municipio;
import io.api.base.domain.Pais;
import io.api.base.service.generics.MunicipioServices;
import io.api.base.service.generics.PaisServices;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/municipio")
@Data
public class MunicipioController extends CrudController<Municipio> {

    @Autowired
    private MunicipioServices realServices;

    @PostConstruct
    public void posContructor() {
        this.service = realServices;
    }

}
