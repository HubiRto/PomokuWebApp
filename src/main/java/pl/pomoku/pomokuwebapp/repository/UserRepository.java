package pl.pomoku.pomokuwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pomoku.pomokuwebapp.entity.AppUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
}
