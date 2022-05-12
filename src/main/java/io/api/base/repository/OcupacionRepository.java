package io.api.base.repository;

import io.api.base.domain.Ocupacion;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OcupacionRepository extends PagingAndSortingRepository<Ocupacion, Long> {


}

