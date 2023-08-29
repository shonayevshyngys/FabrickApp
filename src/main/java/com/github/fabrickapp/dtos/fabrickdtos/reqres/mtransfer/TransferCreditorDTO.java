package com.github.fabrickapp.dtos.fabrickdtos.reqres.mtransfer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferCreditorDTO {
    private String name; //required
    private TransferAccountDTO account; //required
    private TransferAddressDTO address;
}
