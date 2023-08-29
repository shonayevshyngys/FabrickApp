package com.github.fabrickapp.dtos.fabrickdtos;

import lombok.Data;

import java.util.List;

@Data
public class FabrickBaseDTO<T> {

    private String status;
    private List<FabrickError> errors;
    private T payload;

}
