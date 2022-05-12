package io.api.base.service.generics.impl;


import io.api.base.domain.Authority;
import io.api.base.domain.Pais;
import io.api.base.repository.AuthorityRepository;
import io.api.base.repository.PaisRepository;
import io.api.base.service.generics.AuthorityServices;
import io.api.base.service.generics.PaisServices;
import io.api.base.service.generics.cruds.CrudServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class AuthorityServicesImpl extends CrudServicesImpl<Authority> implements AuthorityServices<Authority> {

    @Autowired
    private AuthorityRepository realRepository;


    @PostConstruct
    public void posContructor() {
        this.setRepository(realRepository);
    }


    public Optional<Authority> UpdateById(long id, Authority element) {
        Optional<Authority> optionalCurrentCompany = this.repository.findById(id);
        if (optionalCurrentCompany.isPresent()) {
            Authority currentProduct = optionalCurrentCompany.get();

            if (element.getName() != null) {
                currentProduct.setName(element.getName());
            }


            return Optional.of((Authority) this.repository.save(currentProduct));
        }
        return Optional.empty();

    }


}
