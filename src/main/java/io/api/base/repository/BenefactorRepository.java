package io.api.base.repository;

import io.api.base.domain.Benefactor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenefactorRepository extends PagingAndSortingRepository<Benefactor, Long> {


}

