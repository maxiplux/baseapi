package io.api.base.controllers.generics;


import io.api.base.domain.Departamento;
import io.api.base.domain.EmailDataSource;
import io.api.base.service.generics.DepartamentoServices;
import io.api.base.service.generics.EmailDataSourceServices;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/email-data-source")
@Data
public class EmailDataSourceController extends CrudController<EmailDataSource> {

    @Autowired
    private EmailDataSourceServices realServices;

    @PostConstruct
    public void posContructor() {
        this.service = realServices;
    }

}
