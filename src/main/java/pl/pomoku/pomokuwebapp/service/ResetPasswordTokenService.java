package pl.pomoku.pomokuwebapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pomoku.pomokuwebapp.entity.AppUser;
import pl.pomoku.pomokuwebapp.entity.ResetPasswordToken;
import pl.pomoku.pomokuwebapp.repository.ResetPasswordTokenRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResetPasswordTokenService {
    private final ResetPasswordTokenRepository repository;
    public void createResetPasswordToken(AppUser appUser){
        String token = generateToken();
        ResetPasswordToken resetPasswordToken = ResetPasswordToken.builder()
                .token(token)
                .appUser(appUser)
                .build();
        repository.save(resetPasswordToken);
    }


    private String generateToken(){
        return UUID.randomUUID().toString();
    }
}
