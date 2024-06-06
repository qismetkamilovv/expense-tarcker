package az.keytd.expensetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.keytd.expensetracker.dto.AccountCreateRequest;
import az.keytd.expensetracker.entities.Account;
import az.keytd.expensetracker.entities.User;
import az.keytd.expensetracker.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    private AccountService accountsService;

    @GetMapping("userId")
    public List<Account> findAllByUserId(Long userId) {
        return accountsService.findAllByUserId(userId);
    }

    @PutMapping("increaseBalance")
    public ResponseEntity<Account> increaseBalance(@RequestParam Long id, @RequestParam Double balance) {
        accountsService.increaseBalance(id, balance);
        return ResponseEntity.ok().build();
    }

    @PutMapping("decraseBalance")
    public ResponseEntity<Account> decraseBalance(@RequestParam Long id, @RequestParam Double balance) {
        accountsService.decraseBalance(id, balance);
        return ResponseEntity.ok().build();
    }

    @PostMapping("create")
    @Operation(summary = "create account",
            responses = {
                    @ApiResponse(description = "account is created", responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Account.class)))
            }) 
    public Account createAccount(@AuthenticationPrincipal User user, @RequestBody AccountCreateRequest request) {
        return accountsService.createAccount(request);
    }

}
