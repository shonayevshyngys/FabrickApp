package com.github.common.dtos.fabrickdtos;

import com.github.common.dtos.fabrickdtos.reqres.BalancePayloadDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class BalanceDTO extends FabrickBaseDTO<BalancePayloadDTO> implements Serializable {
    @Serial
    private static final long serialVersionUID = 7156526077883281625L;
}
