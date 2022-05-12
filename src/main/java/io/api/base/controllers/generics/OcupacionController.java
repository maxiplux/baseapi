package io.api.base.controllers.generics;


import io.api.base.domain.Ocupacion;
import io.api.base.domain.Pais;
import io.api.base.service.generics.OcupacionServices;
import io.api.base.service.generics.PaisServices;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/ocupacion")
@Data
public class OcupacionController extends CrudController<Ocupacion> {

    @Autowired
    private OcupacionServices realServices;

    @PostConstruct
    public void posContructor() {
        this.service = realServices;
    }

}
