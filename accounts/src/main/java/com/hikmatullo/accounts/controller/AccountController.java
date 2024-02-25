package com.hikmatullo.accounts.controller;

import com.hikmatullo.app.dto.CustomerDto;
import com.hikmatullo.app.payload.ApiResponse;
import com.hikmatullo.app.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
@Tag(
        name = "AccountController for crud operations",
        description = "simple crud operations"
)
public class AccountController {
    private final IAccountService accountService;



    @io.swagger.v3.oas.annotations.responses.ApiResponse (
            responseCode = "200",
            description = "successful operation, HTTP Status OK",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CustomerDto.class)
            )
    )
    @Operation(
            summary = "find customer by their phone number",
            description = "this api returns customer by their phone number"
    )
    @GetMapping
    public ResponseEntity<CustomerDto> fetchAccount(@RequestParam String mobileNumber) {
        CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
        return ResponseEntity
                .ok(customerDto);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountService.save(customerDto);

        return ResponseEntity
                .ok(new ApiResponse("created successfully", HttpStatus.OK));
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateAccount(@RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountService.updateAccount(customerDto);
        if(isUpdated)
            return ResponseEntity
                    .ok(new ApiResponse("updated successfully", HttpStatus.OK));
        else
            return ResponseEntity
                    .ok(new ApiResponse("updated failed", HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteAccount(@RequestParam String mobileNumber) {
        boolean isDeleted = accountService.deleteAccount(mobileNumber);
        if(isDeleted)
            return ResponseEntity
                    .ok(new ApiResponse("deleted successfully", HttpStatus.OK));
        else
            return ResponseEntity
                    .ok(new ApiResponse("deleted failed", HttpStatus.BAD_REQUEST));
    }
}
