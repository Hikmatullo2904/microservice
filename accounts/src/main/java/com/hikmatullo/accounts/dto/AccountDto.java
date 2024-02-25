package com.hikmatullo.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Account DTO")
public class AccountDto {

    private Long accountNumber;

    private String accountType;

    private String branchAddress;
}
