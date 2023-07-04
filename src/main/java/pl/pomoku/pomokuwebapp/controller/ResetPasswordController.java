package pl.pomoku.pomokuwebapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.pomoku.pomokuwebapp.dto.ResetPasswordDto;
import pl.pomoku.pomokuwebapp.dto.ResetPasswordRequestDto;
import pl.pomoku.pomokuwebapp.entity.AppUser;
import pl.pomoku.pomokuwebapp.service.ResetPasswordTokenService;
import pl.pomoku.pomokuwebapp.service.UserService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ResetPasswordController {
    private final UserService userService;
    private final ResetPasswordTokenService resetPasswordTokenService;
    private final JavaMailSender javaMailSender;

    @PostMapping("/reset-password-request")
    public ResponseEntity<String> createEmailLink(@RequestBody ResetPasswordRequestDto resetPasswordDto){
        Optional<AppUser> optionalUser = userService.findByEmail(resetPasswordDto.email());
        if(optionalUser.isEmpty()){
            return ResponseEntity.badRequest().body("Użytkownik o podanym adresie e-mail nie istnieje.");
        }
        AppUser user = optionalUser.get();
        resetPasswordTokenService.createResetPasswordToken(user);
        String resetToken = generateResetToken();

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
