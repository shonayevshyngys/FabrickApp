package com.github.fabrickapp.dtos.fabrickdtos.reqres.mtransfer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferAddressDTO {
    private String address;
    private String city;
    private String countryCode;
}
