package io.api.base.service.generics.impl;


import io.api.base.domain.Ocupacion;
import io.api.base.repository.OcupacionRepository;
import io.api.base.repository.PaisRepository;
import io.api.base.service.generics.OcupacionServices;
import io.api.base.service.generics.cruds.CrudServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class OcupacionServicesImpl extends CrudServicesImpl<Ocupacion> implements OcupacionServices<Ocupacion> {

    @Autowired
    private OcupacionRepository realRepository;


    @PostConstruct
    public void posContructor() {
        this.setRepository(realRepository);
    }


    public Optional<Ocupacion> UpdateById(long id, Ocupacion element) {
        Optional<Ocupacion> optionalCurrentCompany = this.repository.findById(id);
        if (optionalCurrentCompany.isPresent()) {
            Ocupacion currentProduct = optionalCurrentCompany.get();

            if (element.getNombre() != null) {
                currentProduct.setNombre(element.getNombre());
            }


            return Optional.of((Ocupacion) this.repository.save(currentProduct));
        }
        return Optional.empty();

    }


}
