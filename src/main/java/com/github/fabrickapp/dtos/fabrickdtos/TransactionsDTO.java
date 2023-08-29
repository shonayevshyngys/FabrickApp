package com.github.fabrickapp.dtos.fabrickdtos;

import com.github.fabrickapp.dtos.fabrickdtos.reqres.ListDTO;
import com.github.fabrickapp.dtos.fabrickdtos.reqres.transaction.TransactionDTO;
import lombok.Data;

@Data
public class TransactionsDTO extends FabrickBaseDTO<ListDTO<TransactionDTO>>{
}
