package com.springProject.boot.controllers;

import com.springProject.boot.services.AccountCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/account-card")
@Tag(name = "Account Card", description = "Operations to link accounts and cards")
public class AccountCardController {

    @Autowired
    AccountCardService accountCardService;

    @PostMapping("/link-account-card/{accountId}/{cardId}")
    @Operation(
            summary = "Link Account with Card",
            description = "Links an existing account with an existing card by their UUIDs"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account successfully linked to the card"),
            @ApiResponse(responseCode = "400", description = "Invalid account or card ID provided"),
            @ApiResponse(responseCode = "404", description = "Account or Card not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<UUID> linkAccountCard(
            @Parameter(description = "UUID of the account to link", required = true)
            @PathVariable UUID accountId,

            @Parameter(description = "UUID of the card to link", required = true)
            @PathVariable UUID cardId
    ) throws Exception {
        UUID linkedId = accountCardService.linkAccountCard(accountId, cardId);
        return new ResponseEntity<>(linkedId, HttpStatus.CREATED);
    }
}
