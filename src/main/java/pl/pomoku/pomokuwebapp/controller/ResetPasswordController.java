package pl.pomoku.pomokuwebapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.pomoku.pomokuwebapp.dto.ChangePasswordDto;
import pl.pomoku.pomokuwebapp.dto.ResetPasswordRequestDto;
import pl.pomoku.pomokuwebapp.entity.AppUser;
import pl.pomoku.pomokuwebapp.entity.ResetPasswordToken;
import pl.pomoku.pomokuwebapp.service.ResetPasswordTokenService;
import pl.pomoku.pomokuwebapp.service.UserService;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ResetPasswordController {
    private final UserService userService;
    private final ResetPasswordTokenService resetPasswordTokenService;
    private final JavaMailSender javaMailSender;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        String token = changePasswordDto.token();
        ResetPasswordToken resetPasswordToken = resetPasswordTokenService.findByToken(token);
        if(resetPasswordToken == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Brak tokenu.");
        }
        AppUser user = resetPasswordToken.getAppUser();
        user.setPassword(
                passwordEncoder.encode(changePasswordDto.changedPassword()));
        userService.update(user);
        resetPasswordTokenService.removeToken(resetPasswordToken);
        return ResponseEntity.ok("Hasło zmienione.");
    }
        @PostMapping("/verify-token")
        public ResponseEntity<String> verifyToken(@RequestBody Map<String, Object> request) {
            String token = (String) request.get("token");
        if (isValidToken(token)) {
                return ResponseEntity.ok("Token is valid.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token.");
            }
    }

    private boolean isValidToken(String token) {
        ResetPasswordToken passwordToken = resetPasswordTokenService.findByToken(token);
        if(passwordToken == null){
            return false;
        }
        if(!passwordToken.getExpiredAt().isAfter(LocalDateTime.now())){
            return false;
        }
        return true;
    }

    @PostMapping("/reset-password-request")
    public ResponseEntity<String> createEmailLink(@RequestBody ResetPasswordRequestDto resetPasswordDto){
        Optional<AppUser> optionalUser = userService.findByEmail(resetPasswordDto.email());
        if(optionalUser.isEmpty()){
            return ResponseEntity.badRequest().body("Użytkownik o podanym adresie e-mail nie istnieje.");
        }
        AppUser user = optionalUser.get();
        String resetToken = generateResetToken();
        resetPasswordTokenService.createResetPasswordToken(user, resetToken);

        String resetLink = "http://localhost:5173/reset-password/reset?token=" + resetToken;
        sendResetEmail(user.getEmail(), resetLink);

        return ResponseEntity.ok("Wiadomość e-mail z linkiem resetu hasła została wysłana.");
    }

    private String generateResetToken() {
        return UUID.randomUUID().toString();
    }

    private void sendResetEmail(String email, String resetLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Reset Password");
        message.setText("Click the link below to reset your password:\n" + resetLink);
        javaMailSender.send(message);
    }
}
