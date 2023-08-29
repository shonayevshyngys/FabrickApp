package com.github.fabrickapp.dtos.fabrickdtos.reqres.mtransfer;

import lombok.Data;

@Data
public class TransferTaxReliefDTO {
    private String taxReliefId;
    private boolean isCondoUpgrade;
    private String creditorFiscalCode;
    private String beneficiaryType;
    private TransferNaturalPersonBeneficiaryDTO naturalPersonBeneficiary;
    private TransferLegalPersonBeneficiaryDTO legalPersonBeneficiary;
}
