package com.github.common.dtos.fabrickdtos.reqres.transaction;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TypeDTO implements Serializable {
    private String enumeration;
    private String value;
}
