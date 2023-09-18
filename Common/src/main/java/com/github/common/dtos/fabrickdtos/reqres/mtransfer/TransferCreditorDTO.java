package com.github.common.dtos.fabrickdtos.reqres.mtransfer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferCreditorDTO {
    private String name; //required
    private TransferAccountDTO account; //required
    @JsonInclude(JsonInclude.Include.NON_NULL) private TransferAddressDTO address;
}
