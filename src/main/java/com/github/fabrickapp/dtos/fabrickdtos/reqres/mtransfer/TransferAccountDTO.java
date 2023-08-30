package com.github.fabrickapp.dtos.fabrickdtos.reqres.mtransfer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferAccountDTO {
    private String accountCode; //required
    @JsonInclude(JsonInclude.Include.NON_NULL) private String bicCode;
}
