package com.github.paymentprocessor.service.repo;

import com.github.paymentprocessor.model.Transfer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransferRepository extends CrudRepository<Transfer, Long>, JpaSpecificationExecutor<Transfer> {
    List<Transfer> findAllByStatusIsNotNullAndErrorCodeIsNotNull();
    List<Transfer> findAllByStatusIsNotNullAndErrorCodeIsNull();
}
