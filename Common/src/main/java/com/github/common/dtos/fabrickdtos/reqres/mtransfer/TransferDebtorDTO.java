package com.github.common.dtos.fabrickdtos.reqres.mtransfer;

import lombok.Data;

@Data
public class TransferDebtorDTO {
    private String name;
    private TransferAccountDTO account;
}
