package com.hikmatullo.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(name = "Cards",
        description = "Schema to hold Card information"
)
@Data
public class CustomerDetailsDto {
    @Schema(
            description = "customer name", example = "Hikmatullo"
    )
    @NotBlank(message = "name cannot be blank")
    private String name;

    @Email(message = "email is not valid")
    @NotBlank(message = "email cannot be blank")
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\d{9}$")
    private String phoneNumber;
    @Schema(
        description = "Customer Account information"
    )
    private AccountDto accountDto;

    @Schema(
        description = "Customer Cards information"
    )
    private CardsDto cardsDto;

    @Schema(
        description = "Customer Loans information"
    )
    private LoansDto loansDto;
}
