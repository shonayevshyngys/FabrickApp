package com.github.fabrickapp.dtos.fabrickdtos.reqres.mtransfer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferAccountDTO {
    private String accountCode; //required
    private String bicCode;
}
