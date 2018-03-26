package app.services;

import app.models.Account;
import app.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Primary
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, long id) {
        if(this.accountRepository.findOne(id).getBallance().compareTo(money) > 0){
            this.accountRepository.findOne(id)
                    .setBallance(this.accountRepository.findOne(id).getBallance().subtract(money));
        }

    }

    @Override
    public void transferMoney(BigDecimal money, long id) {
        if(this.accountRepository.findOne(id).getBallance().compareTo(BigDecimal.ZERO) > 0){
            this.accountRepository.findOne(id)
                    .setBallance(this.accountRepository.findOne(id).getBallance().add(money));
        }
    }

    @Override
    public void addAccount(Account account) {
        this.accountRepository.save(account);
    }
}
