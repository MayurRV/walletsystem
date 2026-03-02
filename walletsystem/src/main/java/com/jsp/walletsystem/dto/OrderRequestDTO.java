package com.jsp.walletsystem.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequestDTO {

    @NotNull
    @Min(1)
    private Double amount;
}
