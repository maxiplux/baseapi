package io.api.base.controllers.generics;


import io.api.base.domain.Benefactor;
import io.api.base.domain.Pais;
import io.api.base.service.generics.BenefactorServices;
import io.api.base.service.generics.PaisServices;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/benefactor")
@Data
public class BenefactorController extends CrudController<Benefactor> {

    @Autowired
    private BenefactorServices realServices;

    @PostConstruct
    public void posContructor() {
        this.service = realServices;
    }

}
