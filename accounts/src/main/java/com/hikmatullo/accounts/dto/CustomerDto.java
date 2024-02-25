package com.hikmatullo.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Customer dto class for customer entity",
        description = "this dto class is used for customer entity for request and response"
)
public class CustomerDto {

    @Schema(
            description = "customer name", example = "Hikmatullo"
    )
    @NotBlank(message = "name cannot be blank")
    private String name;

    @Email(message = "email is not valid")
    @NotBlank(message = "email cannot be blank")
    private String email;

    @Pattern(regexp = "^\\d{9}$")
    private String phoneNumber;

    private AccountDto accountDto;


}
