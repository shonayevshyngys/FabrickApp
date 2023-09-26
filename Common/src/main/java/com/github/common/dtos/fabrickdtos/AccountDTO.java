package com.github.common.dtos.fabrickdtos;

import com.github.common.dtos.fabrickdtos.reqres.AccountPayloadDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class AccountDTO extends FabrickBaseDTO<AccountPayloadDTO> implements Serializable {
}
