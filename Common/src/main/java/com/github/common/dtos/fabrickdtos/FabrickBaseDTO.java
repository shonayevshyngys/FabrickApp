package com.github.common.dtos.fabrickdtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FabrickBaseDTO<T> implements Serializable {

    private String status;
    private List<FabrickError> errors;
    private T payload;

}
