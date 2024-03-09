package az.keytd.expensetracker.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import az.keytd.expensetracker.entities.Income;
import az.keytd.expensetracker.service.IncomeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("income")
public class IncomeController {
    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @Operation(
            summary = "Get all incomes",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation")
            }
    )
    @GetMapping("all")
    public List<Income> getAll(){
        return incomeService.getAll();
    }

    @Operation(
            summary = "Add Income",
            responses = {
                @ApiResponse(
                          responseCode = "200",
                          description = "Successful operation",
                          headers = {
                              @Header(name = "access-token",
                                      description = "Access token to authenticate the user")
                          })
            }
    )
    @PutMapping ("income/add")
    public ResponseEntity<Income> income(Long userId, Double amount ){
        incomeService.income(userId, amount);
        return ResponseEntity.ok().build();
    }
}