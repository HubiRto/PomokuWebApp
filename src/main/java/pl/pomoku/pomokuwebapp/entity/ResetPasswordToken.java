package pl.pomoku.pomokuwebapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;
    private Date expiredAt;
    @OneToOne(targetEntity = AppUser.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "app_user_id")
    private AppUser appUser;
}
