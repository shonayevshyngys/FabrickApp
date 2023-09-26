package com.github.common.dtos.fabrickdtos;

import com.github.common.dtos.fabrickdtos.reqres.transaction.TransactionDTO;
import com.github.common.dtos.fabrickdtos.reqres.ListDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TransactionsDTO extends FabrickBaseDTO<ListDTO<TransactionDTO>> implements Serializable {
}
