package io.api.base.repository;


import io.api.base.domain.EmailDataSource;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailDataSourceRepository extends PagingAndSortingRepository<EmailDataSource, Long> {

}
