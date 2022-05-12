package io.api.base.service;


import io.api.base.domain.BatchEmail;
import io.api.base.domain.entites.dto.BatchEmailDto;

public interface BatchEmailServices {

    BatchEmail createEmailBatch(BatchEmailDto batchEmail);

    BatchEmail updateEmailBatch(Long Id, BatchEmailDto batchEmail);
}
