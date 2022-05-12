package io.api.base.service.generics.impl;


import io.api.base.domain.EmailDataSource;
import io.api.base.domain.Pais;
import io.api.base.repository.EmailDataSourceRepository;
import io.api.base.repository.PaisRepository;
import io.api.base.service.generics.EmailDataSourceServices;
import io.api.base.service.generics.PaisServices;
import io.api.base.service.generics.cruds.CrudServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class EmailDataSourceServicesImpl extends CrudServicesImpl<EmailDataSource> implements EmailDataSourceServices<EmailDataSource> {

    @Autowired
    private EmailDataSourceRepository realRepository;


    @PostConstruct
    public void posContructor() {
        this.setRepository(realRepository);
    }


    public Optional<EmailDataSource> UpdateById(long id, EmailDataSource element) {
        Optional<EmailDataSource> optionalCurrentCompany = this.repository.findById(id);
        if (optionalCurrentCompany.isPresent()) {
            EmailDataSource currentProduct = optionalCurrentCompany.get();

            if (element.getName() != null) {
                currentProduct.setName(element.getName());
            }


            return Optional.of((EmailDataSource) this.repository.save(currentProduct));
        }
        return Optional.empty();

    }


}
