package app;

import app.models.Account;
import app.models.User;
import app.services.AccountServiceImpl;
import app.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {
    private UserServiceImpl userService;
    private AccountServiceImpl accountService;

    @Autowired
    public ConsoleRunner(UserServiceImpl userService, AccountServiceImpl accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... strings) throws Exception {
        User user = new User("Pesho", 25, new HashSet<Account>());
        Account account = new Account(BigDecimal.valueOf(255.56), user);
        Account secondAccount = new Account(BigDecimal.valueOf(1235.15), user);
        user.getAccounts().add(account);
        user.getAccounts().add(secondAccount);
        userService.registerUser(user);
        accountService.addAccount(account);
        accountService.addAccount(secondAccount);
        accountService.transferMoney(BigDecimal.valueOf(100), 1L);
        accountService.withdrawMoney(BigDecimal.valueOf(200), secondAccount.getId());
    }
}
