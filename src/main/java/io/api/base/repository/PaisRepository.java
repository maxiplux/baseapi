package io.api.base.repository;

import io.api.base.domain.Pais;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends PagingAndSortingRepository<Pais, Long> {

}

