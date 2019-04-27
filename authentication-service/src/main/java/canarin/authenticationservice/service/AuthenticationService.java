package canarin.authenticationservice.service;

import canarin.authenticationservice.exception.EntityNotFoundException;
import canarin.authenticationservice.payload.JWTTokenResponse;
import canarin.authenticationservice.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private AccountRepository accountRepository;
    private JwtTokenService jwtTokenService;
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);


    public AuthenticationService(AccountRepository accountRepository, JwtTokenService jwtTokenService, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.jwtTokenService = jwtTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public JWTTokenResponse generateJWTToken(String username, String password) {
        return accountRepository.findOneByUsername(username)
            .filter(account ->  passwordEncoder.matches(password, account.getPassword()))
            .map(account -> new JWTTokenResponse(jwtTokenService.generateToken(username)))
            .orElseThrow(() ->  new EntityNotFoundException("Account not found"));
    }
}
