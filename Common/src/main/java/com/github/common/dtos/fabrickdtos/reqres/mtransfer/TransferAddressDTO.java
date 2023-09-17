package com.github.common.dtos.fabrickdtos.reqres.mtransfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferAddressDTO {
    private String address;
    private String city;
    private String countryCode;
}
