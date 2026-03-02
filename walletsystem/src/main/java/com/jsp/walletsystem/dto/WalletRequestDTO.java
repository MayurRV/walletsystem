package com.jsp.walletsystem.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

import lombok.Data;

@Data
public class WalletRequestDTO{

    @NotNull
    private Long clientId;

    @NotNull
    @Min(1)
    private Double amount;
}