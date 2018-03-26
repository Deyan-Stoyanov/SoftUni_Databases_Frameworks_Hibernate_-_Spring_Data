package app.services;

import app.models.Account;

import java.math.BigDecimal;

public interface AccountService {
    void withdrawMoney(BigDecimal money, long id);
    void transferMoney(BigDecimal money, long id);
    void addAccount(Account account);
}
