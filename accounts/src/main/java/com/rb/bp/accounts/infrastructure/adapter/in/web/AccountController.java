package com.rb.bp.accounts.infrastructure.adapter.in.web;

import com.rb.bp.accounts.application.port.in.CRUDAccountUseCase;
import com.rb.bp.accounts.application.service.AccountService;
import com.rb.bp.accounts.domain.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final CRUDAccountUseCase useCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account create(@RequestBody Account account) {
        return useCase.createAccount(account);
    }

    @GetMapping("/{id}")
    public Account findById(@PathVariable Long id) {
        return useCase.findById(id);
    }

    @PatchMapping("/{id}")
    public Account update(@PathVariable Long id, @RequestBody Account account) {
        return useCase.updateAccount(id, account);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        useCase.deleteAccount(id);
    }
}
