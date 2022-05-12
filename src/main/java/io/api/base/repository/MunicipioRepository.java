package io.api.base.repository;

import io.api.base.domain.Municipio;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipioRepository extends PagingAndSortingRepository<Municipio, Long> {

}

