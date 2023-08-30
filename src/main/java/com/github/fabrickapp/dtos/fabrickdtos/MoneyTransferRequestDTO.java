package com.github.fabrickapp.dtos.fabrickdtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.fabrickapp.dtos.fabrickdtos.reqres.mtransfer.TransferCreditorDTO;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MoneyTransferRequestDTO {
    @JsonProperty public TransferCreditorDTO creditor; //required
    @JsonProperty public String executionDate; //required if inInstant if false
    @JsonProperty public String description; //required max140 chars
    @JsonProperty public double amount;
    @JsonProperty public String currency; //required

}
