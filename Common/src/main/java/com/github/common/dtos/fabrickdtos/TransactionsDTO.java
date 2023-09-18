package com.github.common.dtos.fabrickdtos;

import com.github.common.dtos.fabrickdtos.reqres.transaction.TransactionDTO;
import com.github.common.dtos.fabrickdtos.reqres.ListDTO;
import lombok.Data;

@Data
public class TransactionsDTO extends FabrickBaseDTO<ListDTO<TransactionDTO>>{
}
