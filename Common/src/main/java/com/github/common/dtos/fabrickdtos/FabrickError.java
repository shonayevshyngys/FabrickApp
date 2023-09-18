package com.github.common.dtos.fabrickdtos;

import lombok.Data;

@Data
public class FabrickError {
    private String code;
    private String description;
    private String params;
}
