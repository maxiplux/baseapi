package io.api.base.repository;

import io.api.base.domain.Departamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends PagingAndSortingRepository<Departamento, Long> {

}

