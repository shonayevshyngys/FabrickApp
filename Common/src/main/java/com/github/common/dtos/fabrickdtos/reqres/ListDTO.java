package com.github.common.dtos.fabrickdtos.reqres;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class ListDTO<T> implements Serializable {
    List<T> list;
}
