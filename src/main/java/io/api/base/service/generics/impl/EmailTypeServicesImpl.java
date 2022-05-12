package io.api.base.service.generics.impl;


import io.api.base.domain.EmailType;
import io.api.base.domain.Pais;
import io.api.base.repository.EmailTypeRepository;
import io.api.base.repository.PaisRepository;
import io.api.base.service.generics.EmailTypeServices;
import io.api.base.service.generics.PaisServices;
import io.api.base.service.generics.cruds.CrudServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class EmailTypeServicesImpl extends CrudServicesImpl<EmailType> implements EmailTypeServices<EmailType> {

    @Autowired
    private EmailTypeRepository realRepository;


    @PostConstruct
    public void posContructor() {
        this.setRepository(realRepository);
    }


    public Optional<EmailType> UpdateById(long id, EmailType element) {
        Optional<EmailType> optionalCurrentCompany = this.repository.findById(id);
        if (optionalCurrentCompany.isPresent()) {
            EmailType currentProduct = optionalCurrentCompany.get();

            if (element.getName() != null) {
                currentProduct.setName(element.getName());
            }


            return Optional.of((EmailType) this.repository.save(currentProduct));
        }
        return Optional.empty();

    }


}
