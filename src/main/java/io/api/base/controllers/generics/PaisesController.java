package io.api.base.controllers.generics;


import io.api.base.domain.Pais;

import io.api.base.service.generics.PaisServices;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/pais")
@Data
public class PaisesController extends CrudController<Pais> {

    @Autowired
    private PaisServices realServices;

    @PostConstruct
    public void posContructor() {
        this.service = realServices;
    }

}
