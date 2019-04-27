package canarin.authenticationservice.repository;

import canarin.authenticationservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findOneByUsername(String username);
}
