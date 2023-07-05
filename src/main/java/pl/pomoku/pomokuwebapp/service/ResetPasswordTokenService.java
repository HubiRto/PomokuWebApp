package pl.pomoku.pomokuwebapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pomoku.pomokuwebapp.entity.AppUser;
import pl.pomoku.pomokuwebapp.entity.ResetPasswordToken;
import pl.pomoku.pomokuwebapp.repository.ResetPasswordTokenRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResetPasswordTokenService {
    private final ResetPasswordTokenRepository repository;

    public ResetPasswordToken findByToken(String token){
        return repository.findByToken(token);
    }

    public void createResetPasswordToken(AppUser appUser, String resetToken){
        ResetPasswordToken resetPasswordToken = ResetPasswordToken.builder()
                .token(resetToken)
                .appUser(appUser)
                .expiredAt(LocalDateTime.now().plusMinutes(15))
                .build();
        repository.save(resetPasswordToken);
    }

    public void removeToken(ResetPasswordToken token){
        repository.delete(token);
    }
}
