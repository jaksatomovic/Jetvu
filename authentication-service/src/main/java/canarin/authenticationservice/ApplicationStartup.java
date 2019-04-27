package canarin.authenticationservice;

import canarin.authenticationservice.model.Account;
import canarin.authenticationservice.repository.AccountRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;

    public ApplicationStartup(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        accountRepository.deleteAll();;
        Account account = new Account();
        account.setUsername("admin");
        account.setEmail("admin@admin.com");
        account.setPassword(passwordEncoder.encode("admin"));

        accountRepository.save(account);
    }
}
