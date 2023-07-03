package pl.pomoku.pomokuwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pomoku.pomokuwebapp.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
