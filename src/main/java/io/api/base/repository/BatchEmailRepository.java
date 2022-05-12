package io.api.base.repository;


import io.api.base.domain.BatchEmail;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchEmailRepository extends PagingAndSortingRepository<BatchEmail, Long> {

}
