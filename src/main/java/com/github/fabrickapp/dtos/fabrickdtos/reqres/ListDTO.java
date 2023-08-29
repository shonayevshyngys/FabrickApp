package com.github.fabrickapp.dtos.fabrickdtos.reqres;

import lombok.Data;

import java.util.List;

@Data
public class ListDTO<T> {
    List<T> list;
}
