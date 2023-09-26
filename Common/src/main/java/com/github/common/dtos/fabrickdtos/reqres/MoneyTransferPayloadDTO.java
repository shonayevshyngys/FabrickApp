package com.github.common.dtos.fabrickdtos.reqres;

import com.github.common.dtos.fabrickdtos.reqres.mtransfer.TransferAmountDTO;
import com.github.common.dtos.fabrickdtos.reqres.mtransfer.TransferCreditorDTO;
import com.github.common.dtos.fabrickdtos.reqres.mtransfer.TransferDebtorDTO;
import com.github.common.dtos.fabrickdtos.reqres.mtransfer.TransferFeeDTO;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@ToString
public class MoneyTransferPayloadDTO {
    private long moneyTransferId;
    private String status;
    private String direction;
    private TransferCreditorDTO creditor;
    private TransferDebtorDTO debtor;
    private String cro;
    private String uri;
    private String trn;
    private String description;
    private String createdDatetime;
    private String accountedDatetime;
    private String debtorValueDate;
    private String creditorValueDate;
    private TransferAmountDTO amount;
    private boolean isUrgent;
    private boolean isInstant;
    private String feeType;
    private String feeAccountId;
    private List<TransferFeeDTO> fees;
    private boolean hasTaxRelief;
}
