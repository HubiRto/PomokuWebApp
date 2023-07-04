package pl.pomoku.pomokuwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pomoku.pomokuwebapp.entity.ResetPasswordToken;

@Repository
public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, Long> {
    ResetPasswordToken findByToken(String token);
}
